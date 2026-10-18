package com.example.notification.listener

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ListenerStateHolder {
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val _lastEventTimestamp = MutableStateFlow(0L)
    val lastEventTimestamp: StateFlow<Long> = _lastEventTimestamp.asStateFlow()

    private val _eventCounter = MutableStateFlow(0)
    val eventCounter: StateFlow<Int> = _eventCounter.asStateFlow()

    fun setConnected(connected: Boolean) {
        _isConnected.value = connected
    }

    fun recordEvent(timestamp: Long = System.currentTimeMillis()) {
        _lastEventTimestamp.value = timestamp
        _eventCounter.value += 1
    }
}
