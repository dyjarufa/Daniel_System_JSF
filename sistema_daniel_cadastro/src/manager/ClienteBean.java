package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import entity.Cliente;
import persistence.ClienteDao;

@ManagedBean
public class ClienteBean {

	private Cliente cliente;
	private ClienteDao daoCli;
	private List<Cliente> listaCli;

	public ClienteBean() {
		cliente = new Cliente();
		daoCli = new ClienteDao();
		listaCli = new ArrayList<Cliente>();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Cliente> getListaCli() {
		listaCli.clear();
		listaCli.addAll(daoCli.pesquisar());
		return listaCli;
	}

	public void setListaCli(List<Cliente> listaCli) {
		this.listaCli = listaCli;
	}
	
	

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void gravar() {

		FacesMessage msg = null;

		try {
			daoCli.inserir(cliente);
			cliente = new Cliente();
			msg = new FacesMessage("Cliente Cadastrado");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro..." + e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		FacesContext.getCurrentInstance().addMessage("formCadastro", msg);

	}
	
	
	public void alterar() {
		FacesMessage msg = null;

		try {
			daoCli.alterar(cliente);
			cliente = new Cliente();
			msg = new FacesMessage("Cliente alterado");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro..." + e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		FacesContext.getCurrentInstance().addMessage("formConsulta", msg);

	}
	
	

	public void excluirCliente(ActionEvent event) {
		FacesMessage msg = null;

		try {
			UIParameter parametro = (UIParameter) event.getComponent().findComponent("cod");
			Integer codCliente = (Integer) parametro.getValue();

			cliente = daoCli.pesquisar(codCliente);

			daoCli.excluir(cliente);
			msg = new FacesMessage("Cliente excluido.");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro: " + e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext.getCurrentInstance().addMessage("formConsulta", msg);

	}
	
	public void filtrar(ActionEvent event) {
		
		FacesMessage msg = null;

		try {
			UIParameter parametro = (UIParameter) event.getComponent().findComponent("filter");
			String cpfCliente = (String) parametro.getValue();

			daoCli.pesquisar(cpfCliente);

		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro: " + e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext.getCurrentInstance().addMessage("formConsulta", msg);

		
	}
	
	
	
	

	// public String formPesquisa() {
	// return "pesquisa?faces-redirect-true";
	// }

}
