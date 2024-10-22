package com.example.chickeninviders.game.viewmodel

internal fun MainViewModel.updateCamera() {
    _camera.value = _camera.value?.move()
    val newCameraPosition = _camera.value?.position
    if (newCameraPosition != lastCameraPosition) {
        lastCameraPosition = newCameraPosition
    }
}