package com.Challenge.ChallengeLiteratura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}