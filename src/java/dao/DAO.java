package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author LabingXEON
 */
public interface DAO<T> {

    /**
     * Crear el <T> objeto (escribirlo en el archivo);
     * @param ob .
     * @return true si se creo exitosamente.
     */
    public boolean crear(T ob) throws FileNotFoundException, IOException;

    /**
     * Busca en el archivo una id y devuelve el objeto de la busqueda.
     * @param id .
     * @return <T> or null.
     */
    public T buscar(Object id) throws FileNotFoundException, IOException;
 
    
}
