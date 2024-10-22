package com.example.chickeninviders.utils

fun Double.degreesToRadians(): Double {
    return this * Math.PI / 180.0
}

fun sphericalToCartesian(
    radius: Double,
    theta: Double,
    phi: Double
): Triple<Double, Double, Double> {
    val x = radius * kotlin.math.sin(theta) * kotlin.math.cos(phi)
    val y = radius * kotlin.math.sin(theta) * kotlin.math.sin(phi)
    val z = radius * kotlin.math.cos(theta)
    return Triple(x, y, z)
}

fun polarToCartesian(radius: Double, angleInRadians: Double): Pair<Double, Double> {
    val x = radius * kotlin.math.cos(angleInRadians)
    val y = radius * kotlin.math.sin(angleInRadians)
    return Pair(x, y)
}

