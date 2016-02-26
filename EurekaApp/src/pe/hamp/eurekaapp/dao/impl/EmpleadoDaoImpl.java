/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.hamp.eurekaapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.hamp.eurekaapp.dao.espec.EmpleadoDaoCrud;
import pe.hamp.eurekaapp.db.AccesoDB;
import pe.hamp.eurekaapp.domain.EmpleadoBean;
import pe.hamp.eurekaapp.util.EurekaUtil;

/**
 *
 * @author Upao
 */
public class EmpleadoDaoImpl
        extends AbstractRowToBean<EmpleadoBean>
        implements EmpleadoDaoCrud {

    @Override
    protected EmpleadoBean rowToBean(ResultSet rs) throws SQLException {
        EmpleadoBean bean = new EmpleadoBean();
        bean.setCodigo(rs.getString("chr_emplcodigo"));
        bean.setPaterno(rs.getString("vch_emplpaterno"));
        bean.setMaterno(rs.getString("vch_emplmaterno"));
        bean.setNombre(rs.getString("vch_emplnombre"));
        bean.setCiudad(rs.getString("vch_emplciudad"));
        bean.setDireccion(rs.getString("vch_empldireccion"));
        bean.setUsuario(rs.getString("vch_emplusuario"));
        bean.setClave(rs.getString("vch_emplclave"));
        return bean;
    }

    @Override
    public EmpleadoBean validar(String usuario, String clave) {
        EmpleadoBean bean = null;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_emplcodigo, vch_emplpaterno, "
                    + "vch_emplmaterno, vch_emplnombre, "
                    + "vch_emplciudad, vch_empldireccion, "
                    + "vch_emplusuario "
                    + "from empleado "
                    + "where vch_emplusuario = ? "
                    + "and vch_emplclave = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, clave);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                bean = new EmpleadoBean();
                bean.setCodigo(rs.getString("chr_emplcodigo"));
                bean.setPaterno(rs.getString("vch_emplpaterno"));
                bean.setMaterno(rs.getString("vch_emplmaterno"));
                bean.setNombre(rs.getString("vch_emplnombre"));
                bean.setCiudad(rs.getString("vch_emplciudad"));
                bean.setDireccion(rs.getString("vch_empldireccion"));
                bean.setUsuario(rs.getString("vch_emplusuario"));
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "Error en el proceso de validaciÃ³n.";
            if (e.getMessage() != null) {
                msg += "\n" + e.getMessage();
            }
            throw new RuntimeException(msg);
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return bean;

    }

    @Override
    public EmpleadoBean traerPorCodigo(String codigo) {
        EmpleadoBean bean = null;
        Connection cn = null;
        try {
          cn = AccesoDB.getConnection();
          String sql = "select chr_emplcodigo, vch_emplpaterno, "
                  + "vch_emplmaterno, vch_emplnombre, "
                  + "vch_emplciudad, "
                  + "vch_empldireccion, "
                  + "vch_emplusuario, vch_emplclave "
                  + "from empleado "
                  + "where chr_emplcodigo = ? ";
          PreparedStatement pstm = cn.prepareStatement(sql);
          pstm.setString(1, codigo);
          ResultSet rs = pstm.executeQuery();
          if (rs.next()) {
            bean = rowToBean(rs);
          }
          rs.close();
          pstm.close();
        } catch (SQLException e) {
          throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
          e.printStackTrace();
          String msg = "Error en el proceso de validaciÃ³n.";
          if (e.getMessage() != null) {
            msg += "\n" + e.getMessage();
          }
          throw new RuntimeException(msg);
        } finally {
          try {
            cn.close();
          } catch (Exception e) {
          }
        }
        return bean;
    }

    @Override
    public List<EmpleadoBean> taerLista(EmpleadoBean bean) {
    
        List<EmpleadoBean> lista = new ArrayList<>();
        Connection cn = null;
        try {
          cn = AccesoDB.getConnection();
          String sql = "select chr_emplcodigo, vch_emplpaterno, "
                  + "vch_emplmaterno, vch_emplnombre, "
                  + "vch_emplciudad, "
                  + "vch_empldireccion, "
                  + "vch_emplusuario, vch_emplclave "
                  + "from empleado "
                  + "where (chr_emplcodigo like ? ) "
                  + "and (vch_emplpaterno like ? "
                  + "and vch_emplmaterno like ? "
                  + "and vch_emplnombre like ?) ";
          PreparedStatement pstm = cn.prepareStatement(sql);
          pstm.setString(1, EurekaUtil.aString(bean.getCodigo()) + "%");
          pstm.setString(2, EurekaUtil.aString(bean.getPaterno()) + "%");
          pstm.setString(3, EurekaUtil.aString(bean.getMaterno()) + "%");
          pstm.setString(4, EurekaUtil.aString(bean.getNombre()) + "%");
          ResultSet rs = pstm.executeQuery();
          while (rs.next()) {
            EmpleadoBean beanEmpleado = rowToBean(rs);
            lista.add(beanEmpleado);
          }
          rs.close();
          pstm.close();
        } catch (SQLException e) {
          throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
          e.printStackTrace();
          String msg = "Error en el proceso de validaciÃ³n.";
          if (e.getMessage() != null) {
            msg += "\n" + e.getMessage();
          }
          throw new RuntimeException(msg);
        } finally {
          try {
            cn.close();
          } catch (Exception e) {
          }
        }
        return lista;
    }

    @Override
    public void insertar(EmpleadoBean bean) {
        
        Connection cn = null;
        try {
          // Obtener la conexiÃ³n
          cn = AccesoDB.getConnection();
          // Habilitar la transacciÃ³n
          cn.setAutoCommit(false);
          // Paso 1: Leer datos del contador
          String sql = "select int_contitem, int_contlongitud "
                  + "from contador "
                  + "where vch_conttabla = 'Empleado' "
                  + "for update ";
          PreparedStatement pstm = cn.prepareStatement(sql);
          ResultSet rs = pstm.executeQuery();
          if (!rs.next()) {
            throw new SQLException("ERROR: El contador de empleados no existe.");
          }
          int cont = rs.getInt("int_contitem");
          int size = rs.getInt("int_contlongitud");
          rs.close();
          pstm.close();
          // Actualizar contador
          cont++;
          sql = "update contador set int_contitem = ? "
                  + "where vch_conttabla = 'Empleado'";
          pstm = cn.prepareStatement(sql);
          pstm.setInt(1, cont);
          pstm.executeUpdate();
          // Generar CÃ³digo
          String patron = "%0" + size + "d";
          String codigo = String.format(patron, cont);
          // Registrar al cliente
          sql = "insert into empleado(chr_emplcodigo, "
                  + "vch_emplpaterno, vch_emplmaterno, "
                  + "vch_emplnombre, "
                  + "vch_emplciudad, vch_empldireccion, "
                  + "vch_emplusuario, vch_emplclave) "
                  + "values(?,?,?,?,?,?,?,?) ";
          pstm = cn.prepareStatement(sql);
          pstm.setString(1, codigo);
          pstm.setString(2, bean.getPaterno());
          pstm.setString(3, bean.getMaterno());
          pstm.setString(4, bean.getNombre());
          pstm.setString(5, bean.getCiudad());
          pstm.setString(6, bean.getDireccion());
          pstm.setString(7, bean.getUsuario());
          pstm.setString(8, bean.getClave());
          pstm.executeUpdate();
          pstm.close();
          bean.setCodigo(codigo);
          // Confimar transacciÃ³n
          cn.commit();
        } catch (SQLException e) {
          try {
            cn.rollback();
          } catch (Exception e1) {
          }
          throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
          try {
            cn.rollback();
          } catch (Exception e1) {
          }
          throw new RuntimeException("Error en el proceso Registrar Empleado, intentelo mas tarde.");
        } finally {
          try {
            cn.close();
          } catch (Exception e) {
          }
        }
        
    }

    @Override
    public void actualizar(EmpleadoBean bean) {
    
      //Codigo agregado
      Connection cn = null;
      try {
        // Obtener la conexión
        cn = AccesoDB.getConnection();

        PreparedStatement pstm;

        // Actualizar cliente
        String sql = "update empleado set vch_emplpaterno = ?, vch_emplmaterno = ?, "
                + "vch_emplnombre = ?, "
                + "vch_emplciudad = ?, vch_empldireccion = ?, "
                + "vch_emplusuario = ?, vch_emplclave = ? "
                + "where chr_emplcodigo = ? ";
        pstm = cn.prepareStatement(sql);
        pstm.setString(1, bean.getPaterno());
        pstm.setString(2, bean.getMaterno());
        pstm.setString(3, bean.getNombre());
        pstm.setString(4, bean.getCiudad());
        pstm.setString(5, bean.getDireccion());
        pstm.setString(6, bean.getUsuario());
        pstm.setString(7, bean.getClave());
        pstm.setString(8, bean.getCodigo());
        pstm.executeUpdate();
        pstm.close();

      } catch (SQLException e) {

        throw new RuntimeException(e.getMessage());
      } catch (Exception e) {

        throw new RuntimeException("Error en el proceso Actualizar Empleado, intentelo mas tarde.");
      } finally {
        try {
          cn.close();
        } catch (Exception e) {
        }
      }
    }

    @Override
    public void eliminar(String codigo) {
        
          //Codigo agregado
      
        Connection cn = null;
        try {
          // Obtener la conexión
          cn = AccesoDB.getConnection();

          PreparedStatement pstm;

          // Eliminar cliente
          String sql = "delete empleado  "
                  + "where chr_emplcodigo = ? ";
          pstm = cn.prepareStatement(sql);
          pstm.setString(1, codigo);
          pstm.executeUpdate();
          pstm.close();

        } catch (SQLException e) {

          throw new RuntimeException(e.getMessage());
        } catch (Exception e) {

          throw new RuntimeException("Error en el proceso Eliminar Empleado, intentelo mas tarde.");
        } finally {
          try {
            cn.close();
          } catch (Exception e) {
          }
        }
        
    }

}
