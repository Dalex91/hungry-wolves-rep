package com.example.hungrywolves.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConnectionLiveData(context: Context) : LiveData<Boolean>(){

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableSet<Network> = HashSet()

    override fun onActive() {
        cm.apply {
            val networkCapabilities = getNetworkCapabilities(activeNetwork)
            val hasCapabilityInternet = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            if(hasCapabilityInternet == true) {
                CoroutineScope(Dispatchers.IO).launch {
                    val hasInternet = activeNetwork?.let { InternetChecker.execute(it.socketFactory) }
                    if(hasInternet == true){
                        withContext(Dispatchers.Main){
                            activeNetwork?.let { validNetworks.add(it) }
                            checkValidNetworks()
                        }
                    }
                }
            }
            else
                checkValidNetworks()
            networkCallback = createNetworkCallback()
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NET_CAPABILITY_INTERNET)
                .build()
            registerNetworkCallback(networkRequest, networkCallback)
        }
    }

    override fun onInactive() {
        cm.unregisterNetworkCallback(networkCallback)
    }

    private fun checkValidNetworks() {
        postValue(validNetworks.size > 0)
    }

    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            validNetworks.add(network)
            checkValidNetworks()
        }

        override fun onLost(network: Network) {
            validNetworks.remove(network)
            checkValidNetworks()
        }

        override fun onUnavailable() {
            checkValidNetworks()
        }
    }
}