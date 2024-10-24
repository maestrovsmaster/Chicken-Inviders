package com.example.chickeninviders


const val GLOBAL_TIMER_DELAY = 12L // This is the delay in milliseconds between each game loop iteration

const val MAX_LIFES = 5 // This is the maximum number of lives that the player can have

const val PLAYER_WEAPON_RELOAD_TIME = 400L // This is the time in milliseconds that the player takes to reload his weapon
const val CHICKEN_WEAPON_RELOAD_TIME = 600L // This is the time in milliseconds that the chickens take to reload their weapons

const val CHICKEN_SPEED = 1.2f // This is the speed of the chickens

const val INITIAL_AMMO_COUNT = 10 // This is the initial number of bullets that the player has

const val INITIAL_SHIELDS = 5 // This is the initial number of shields that the player has
const val MAX_SHIELDS = 12 // This is the maximum number of shields that can be collected
const val SHIELD_ACTIVE_TIME = 7000L // This is the time in milliseconds that the shield is active

//Probabilities. Higher values mean that the item will appear LESS often
const val LIFE_PARCEL_PROBABILITY = 10 // This is the probability of a life parcel appearing
const val AMMO_PARCEL_PROBABILITY = 5 // This is the probability of an ammo parcel appearing
const val SHIELD_PARCEL_PROBABILITY = 3 // This is the probability of a shield parcel appearing