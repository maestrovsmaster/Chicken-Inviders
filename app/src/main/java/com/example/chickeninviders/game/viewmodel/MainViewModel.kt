package com.example.chickeninviders.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chickeninviders.game.camera.MainCamera
import com.example.chickeninviders.game.gameplay.TimerHandler
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.utils.Position

class MainViewModel : ViewModel() {

    private val _timer = MutableLiveData<Long>(0)
    val timer: LiveData<Long> = _timer

    private var currentTime = 0L

    private val timerHandler = TimerHandler(
        updateInterval = 7L,
        onTick = {
            updateGameState()
            currentTime += 1
            _timer.postValue(currentTime)
        }
    )


    fun startTimer() {
        timerHandler.start()
    }

    fun stopTimer() {
        timerHandler.stop()
    }


    internal var lastCameraPosition: Position? = null

    internal val _spaceObjects = MutableLiveData<MutableList<PhysicEntity>>()
    val spaceObjects: LiveData<MutableList<PhysicEntity>> = _spaceObjects


    internal val _camera = MutableLiveData<MainCamera>(MainCamera())
    val camera: LiveData<MainCamera> = _camera


    internal val _ammo = MutableLiveData<Int>(10)
    val ammo: LiveData<Int> = _ammo

    internal val _lifes = MutableLiveData<Int>(3)
    val lifes: LiveData<Int> = _lifes


    private val _clickRight = MutableLiveData(false)
    val clickRight: LiveData<Boolean> = _clickRight

    private val _clickLeft = MutableLiveData(false)
    val clickLeft: LiveData<Boolean> = _clickLeft

    public fun clickLeft() {
        _clickLeft.postValue(true)
        _camera.value?.turnLeft()
        moveMySheepLeft()
    }


    public fun clickRight() {
        _clickRight.postValue(true)
        _camera.value?.turnRight()
        moveMySheepRight()
    }

    public fun clickFire() {

        var newValue = _ammo.value!! - 1
        if (newValue < 0) {
            newValue = 0
        }
        _ammo.postValue(newValue)
        if (newValue > 0) {
            shot()
        }
    }


    public fun addSpaceObjects(list: MutableList<PhysicEntity>) {
        val newList = (_spaceObjects.value ?: mutableListOf())
        newList.addAll(list)
        _spaceObjects.value = newList
    }

    public fun addSpaceObject(item: PhysicEntity) {
        val newList = (_spaceObjects.value ?: mutableListOf())
        newList.add(item)
        _spaceObjects.value = newList
    }


}