/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Estructuras.Arbol_Archivo_IdLong;
import Modelo.Curso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Labing I5
 */
public class DaoCurso implements DAO<Curso> {

    private RandomAccessFile archivo;
    private Arbol_Archivo_IdLong arbol;

    public DaoCurso() throws FileNotFoundException, FileNotFoundException {

        archivo = new RandomAccessFile("curso", "rw");
        arbol = new Arbol_Archivo_IdLong("curso");

    }

    @Override
    public boolean crear(Curso ob) throws FileNotFoundException, IOException {
        archivo.seek(archivo.length());

        if (arbol.añadir((long) ob.getId(), (int) archivo.length())) {
            archivo.writeLong(ob.getId());
            archivo.writeUTF(ob.getNombre());
            return true;
        }
        return false;

    }

    @Override
    public Curso buscar(Object id) throws FileNotFoundException, IOException {
        int pos = (int) arbol.getPosArchivo((int) id);
        if (pos != -1) {
            archivo.seek(pos);
            Curso curso = new Curso((int) archivo.readLong(), archivo.readUTF());
            return curso;
        }

        return null;
    }

    public ArrayList<Curso> obtenercursos() throws FileNotFoundException, IOException {
        ArrayList<Curso> cursos = new ArrayList<>();

        RandomAccessFile arbolcurso = new RandomAccessFile("arbolcurso", "rw");
        int n = (int) (arbolcurso.length() / (8 + 12));
        arbolcurso.seek(0);
        for (int i = 0; i < n; i++) {
            int id = (int) arbolcurso.readLong();
            Curso cursi = buscar(id);
            if (cursi != null) {
                cursos.add(cursi);
            }
            arbolcurso.skipBytes(12);

        }
        return cursos;
    }

   
}
