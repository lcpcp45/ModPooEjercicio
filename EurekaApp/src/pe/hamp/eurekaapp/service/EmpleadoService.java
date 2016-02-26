/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.hamp.eurekaapp.service;

import java.util.List;
import pe.hamp.eurekaapp.dao.espec.EmpleadoDaoCrud;
import pe.hamp.eurekaapp.dao.impl.EmpleadoDaoImpl;
import pe.hamp.eurekaapp.domain.EmpleadoBean;

/**
 *
 * @author GARY
 */
public class EmpleadoService {
    private EmpleadoDaoCrud empleadoDao;
    
    public EmpleadoService(){
        empleadoDao=new EmpleadoDaoImpl();
    }
    
    public List<EmpleadoBean> traerEmpleado(EmpleadoBean bean){
        return empleadoDao.taerLista(bean);
    }
    
    public void insertar(EmpleadoBean bean){
        if(EmpleadoValido(bean))
        {
            empleadoDao.insertar(bean);
        }
    }
    
    public void actualizar(EmpleadoBean bean){
        if(EmpleadoValido(bean))
        {
            empleadoDao.actualizar(bean);
        }
    }
    
    public void eliminar(EmpleadoBean bean){
        empleadoDao.eliminar(bean.getCodigo());
    }
    
    boolean EmpleadoValido(EmpleadoBean bean)
    {
        //Validacion de Ingreso de Datos ... 
        boolean valido=true;
        if (bean.getPaterno() == null || bean.getPaterno().isEmpty()) {
            valido = false;
            throw new RuntimeException("Error, Debe ingresar el Apellido Paterno ... ");
        }
        
        if (bean.getMaterno() == null || bean.getMaterno().isEmpty()) {
            valido = false;
            throw new RuntimeException("Error, Debe ingresar el Apellido Materno ... ");
        }
        
        if (bean.getNombre() == null || bean.getNombre().isEmpty()) {
            valido = false;
            throw new RuntimeException("Error, Debe ingresar el Nombre ... ");
        }
        
        if (bean.getCiudad() == null || bean.getCiudad().isEmpty()) {
            valido = false;
            throw new RuntimeException("Error, Debe ingresar la Ciudad ... ");
        }
        
        if (bean.getDireccion() == null || bean.getDireccion().isEmpty()) {
            valido = false;
            throw new RuntimeException("Error, Debe ingresar la Direccion ... ");
        }
        
        if (bean.getUsuario() == null || bean.getUsuario().isEmpty()) {
            valido = false;
            throw new RuntimeException("Error, Debe ingresar el Usuario ... ");
        }
        
        if (bean.getClave() == null || bean.getClave().isEmpty()) {
            valido = false;
            throw new RuntimeException("Error, Debe ingresar la Clave ... ");
        }
        
        return valido;
    }
    
}
