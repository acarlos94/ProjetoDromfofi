/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloDrone;

public class ControleDrone {
    ConexaoBD conex = new ConexaoBD();
    ModeloDrone mod = new ModeloDrone();
    
    public void salvar(ModeloDrone mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into drones(codigo) values(?)");
            pst.setString(1, mod.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados!\nErro:"+ex);
        }
        
        conex.desconecta();
    } 
    
    public ModeloDrone buscaDrone(ModeloDrone drone){
        conex.conexao();
        conex.executaSql("select * from drones where codigo like'%"+drone.getPesquisa()+"%'");
        try {
            conex.rs.first();
            drone.setId(conex.rs.getInt("id"));
            drone.setCodigo(conex.rs.getString("codigo"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar drone!\nErro:"+ex);
        }
        
        conex.desconecta();
        return drone;
    }
    
    public void editar(ModeloDrone drone){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("update drones set codigo=? where id=?");
            pst.setString(1, drone.getCodigo());
            pst.setInt(2, drone.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados!\nErro:"+ex);
        }
        
        conex.desconecta();
    }
    
    public void excluir(ModeloDrone drone){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from drones where id=?");
            pst.setInt(1, drone.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exluir dados!\nErro:"+ex);
        }
        
        conex.desconecta();
    }
}

