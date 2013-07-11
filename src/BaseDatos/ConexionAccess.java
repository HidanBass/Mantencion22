    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hidan
 */
public class ConexionAccess
{

    public ConexionAccess() 
    {
    }
    
    private static final String driver="sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String db="jdbc:odbc:BaseDatos"; // 
    private static final String user="";
    private static final String pass="";
    private static Connection link=null;
    private PreparedStatement ps=null;
    private static ConexionAccess instance=null;

    static 
    {
       try 
            {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexionAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        try 
        {
            link = DriverManager.getConnection(db,user,pass);
           
        } catch (SQLException ex) {
            Logger.getLogger(ConexionAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConexionAccess getInstance() 
    {
        if (instance==null)
        {
            instance=new ConexionAccess();
        }
        return instance;
    }
    
    public ResultSet ejecutarQuery(String sql) 
    {
        ResultSet rs=null;
        try {
            ps=link.prepareStatement(sql);
            rs=ps.executeQuery();        
        } catch (SQLException ex) {
            Logger.getLogger(ConexionAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void ejecutarIAE(String sql)
    {
        try {
            ps=link.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionAccess.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}