/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ModeloDrone;
import modelo.ModeloUsuario;

/**
 *
 * @author Antonio
 */
public class ControleUsuario {
    ConexaoBD conex = new ConexaoBD();
    ModeloUsuario mod = new ModeloUsuario();
    
    public void salvar(ModeloUsuario mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into usuarios(nome,login,senha) values(?,?,?)");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getLogin());
            pst.setString(3, mod.getSenha());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário!\nErro:"+ex);
        }
        
        conex.desconecta();
    } 
    
    public ModeloUsuario buscaUsuario(ModeloUsuario usuario){
        conex.conexao();
        conex.executaSql("select * from usuarios where nome like'%"+usuario.getPesquisa()+"%'");
        try {
            conex.rs.first();
            usuario.setIdUsuario(conex.rs.getInt("id"));
            usuario.setNome(conex.rs.getString("nome"));
            usuario.setLogin(conex.rs.getString("login"));
            usuario.setSenha(conex.rs.getString("senha"));
        } catch (SQLException ex) {
            usuario = null;
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário!\nErro:"+ex);
        }
        
        conex.desconecta();
        return usuario;
    }
    
    public void editar(ModeloUsuario usuario){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("update usuarios set nome=?,login=?,senha=? where id=?");
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getLogin());
            pst.setString(3, usuario.getSenha());
            pst.setInt(4, usuario.getIdUsuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar usuário!\nErro:"+ex);
        }
        
        conex.desconecta();
    }
    
    public void excluir(ModeloUsuario usuario){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from usuarios where id=?");
            pst.setInt(1, usuario.getIdUsuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exluir usuario!\nErro:"+ex);
        }
        
        conex.desconecta();
    }
    
    public ModeloUsuario verificaLogin(ModeloUsuario usuario){
        conex.conexao();
        conex.executaSql("select * from usuarios where login like'%"+usuario.getPesquisa()+"%'");
        try {
            conex.rs.first();
            usuario.setIdUsuario(conex.rs.getInt("id"));
            usuario.setNome(conex.rs.getString("nome"));
            usuario.setLogin(conex.rs.getString("login"));
            usuario.setSenha(conex.rs.getString("senha"));
        } catch (SQLException ex) {
            usuario = null;
            //JOptionPane.showMessageDialog(null, "Erro ao buscar usuário!\nErro:"+ex);
        }
        
        conex.desconecta();
        return usuario;
    }
}
