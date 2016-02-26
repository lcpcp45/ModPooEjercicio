/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.hamp.eurekaapp.test;

import pe.hamp.eurekaapp.dao.espec.ClienteDaoCrud;
import pe.hamp.eurekaapp.dao.impl.ClienteDaoImpl;
import pe.hamp.eurekaapp.domain.ClienteBean;

/**
 *
 * @author Upao
 */
public class Test01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ClienteBean bean = new ClienteBean();
           
        bean.setPaterno("A");
        bean.setMaterno("B");
        bean.setNombre("C");
        bean.setDni("D");
        bean.setCiudad("E");
        bean.setDireccion("F");
        bean.setTelefono("1");
        bean.setEmail("G");
        
        ClienteDaoCrud clienteDao=new ClienteDaoImpl();
        clienteDao.insertar(bean);
        
        System.out.println("Todo ok. codigo : "+bean.getCodigo());
        
    }
    
}
