package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import persistencia.ProdutoDAO;
import bens.Produto;

@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto;
	private String filtro; 

	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListagem() {
		if(filtro == null){
			
			return ProdutoDAO.listagem("");
			
		}else
		return ProdutoDAO.listagem(filtro);
	}

	public String actionGravar() {
		if (produto.getId() == 0 || produto == null) {
			ProdutoDAO.inserir(produto);
			return actionInserir();
		} else {
			ProdutoDAO.alterar(produto);
			return "lista_produto";
		}
	}
	public String actionInserir(){
		produto = new Produto();
		return "form_produto";
	}
	public String actionExcluir(){
		ProdutoDAO.excluir(produto);
		return "lista_produto";
		
	}
	public String actionAlterar(Produto p){
		produto = p;
		return "form_produto";
	}
}
