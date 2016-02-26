/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.hamp.eurekaapp.test;

import pe.hamp.eurekaapp.dao.espec.EmpleadoDaoCrud;
import pe.hamp.eurekaapp.dao.impl.EmpleadoDaoImpl;
import pe.hamp.eurekaapp.domain.EmpleadoBean;



/**
 *
 * @author Upao
 */
public class Test02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        EmpleadoBean bean = new EmpleadoBean();
           
        bean.setPaterno("Sanchez");
        bean.setMaterno("Garay");
        bean.setNombre("Wilmer Eudardo");
        bean.setCiudad("Chimbote");
        bean.setDireccion("nuevo Chimbote");
        bean.setUsuario("prueba");
        bean.setClave("123");

        
        EmpleadoDaoCrud clienteDao=new EmpleadoDaoImpl();
        clienteDao.insertar(bean);
        
        System.out.println("Todo ok. codigo : "+bean.getCodigo());
        
        /*if(clienteDao.traerPorCodigo("0001")!= null){
            System.out.println("Empleado Existe ...!");
        }
        else{
            System.out.println("Empleado No Existe ...!");
        }*/
            
    }
    
}
