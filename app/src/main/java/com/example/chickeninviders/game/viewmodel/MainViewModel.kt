package com.example.chickeninviders.game.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chickeninviders.GLOBAL_TIMER_DELAY
import com.example.chickeninviders.INITIAL_AMMO_COUNT
import com.example.chickeninviders.INITIAL_SHIELDS
import com.example.chickeninviders.MAX_LIFES
import com.example.chickeninviders.MAX_SHIELDS
import com.example.chickeninviders.PLAYER_WEAPON_RELOAD_TIME
import com.example.chickeninviders.SHIELD_ACTIVE_TIME
import com.example.chickeninviders.game.camera.MainCamera
import com.example.chickeninviders.game.gameplay.TimerHandler
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.utils.Position
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val _timer = MutableLiveData<Long>(0)
    val timer: LiveData<Long> = _timer

    private var currentTime = 0L

    private val timerHandler = TimerHandler(
        updateInterval = GLOBAL_TIMER_DELAY, //7L,
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


    internal val _ammo = MutableLiveData<Int>(INITIAL_AMMO_COUNT)
    val ammo: LiveData<Int> = _ammo

    internal val _lifes = MutableLiveData<Int>(MAX_LIFES)
    val lifes: LiveData<Int> = _lifes

    internal val _shields = MutableLiveData<Int>(INITIAL_SHIELDS)
    val shields: LiveData<Int> = _shields


    private val _clickRight = MutableLiveData(false)
    val clickRight: LiveData<Boolean> = _clickRight

    private val _clickLeft = MutableLiveData(false)
    val clickLeft: LiveData<Boolean> = _clickLeft

    public fun clickLeft() {
        _clickLeft.postValue(true)
      //  _camera.value?.turnLeft()
        moveMySheepLeft()
    }


    public fun clickRight() {
        _clickRight.postValue(true)
      //  _camera.value?.turnRight()
        moveMySheepRight()
    }

    var lastShotTime = System.currentTimeMillis()
    val minInterval = PLAYER_WEAPON_RELOAD_TIME

    public fun clickFire() {

        val currentTime = System.currentTimeMillis()
        if (currentTime - lastShotTime > minInterval) {
            lastShotTime = currentTime
            prepareShot()
        }

    }



    private fun prepareShot() {
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