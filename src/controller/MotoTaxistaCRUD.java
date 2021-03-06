package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Chamado;
import dominio.Empresa;
import dominio.MotoTaxista;
import servico.EmpresaServico;
import servico.MotoTaxistaServico;
import servico.ServicoException;
import servico.ServicoFactory;

@WebServlet("/cliente/MotoTaxistaCRUD")
public class MotoTaxistaCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String VISUALIZAR = "/administrador/visualizarMototaxista.jsp";
	private static String INSERIR_OU_ALTERAR = "/administrador/motoForm.jsp";
	private static String LISTAR = "/administrador/listarMototaxista.jsp";
	private static String ESCOLHER_EMPRESA = "/administrador/mototaxistaEscolherEmpresa.jsp";

	// P�ginas JSP do Apar�cio
	private static String LISTAR_MEDIA = "/administrador/mediaListar.jsp";
	private static String LISTAR_2 = "/administrador/listarMototaxista2.jsp";
	private static String LISTAR_EMPRESA = "/administrador/empresaListar.jsp";
	private static String LISTAR_CHAMADO = "/administrador/listarChamado.jsp";
	private static String FORM_EMPRESA = "/administrador/dataChamadoForm.jsp";
	private static String ERRO = "/publico/erro.jsp";
	private static String ERRO2 = "/administrador/listarMototaxista.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MotoTaxistaServico mototaxistaServico = ServicoFactory.criarMotoTaxistaServico();
		EmpresaServico empresaServico = ServicoFactory.criarEmpresaServico();

		String forward = "";
		String cmd = request.getParameter("cmd");

		if (cmd == null || cmd.equalsIgnoreCase(""))
			cmd = "listar";

		if (cmd.equalsIgnoreCase("deletar")) {
			int cod = Integer.parseInt(request.getParameter("cod"));
			try {
				MotoTaxista mot = mototaxistaServico.buscar(cod);
				if (mot != null) {
					mototaxistaServico.excluir(mot);
				}
				request.setAttribute("lista", mototaxistaServico.buscarTodos());
				forward = LISTAR;
			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
				forward = ERRO;
			} catch (ServicoException s) {
				request.setAttribute("erro", s.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}
		
		else if (cmd.equalsIgnoreCase("editar")) {
			int cod = Integer.parseInt(request.getParameter("cod"));
			try {
				MotoTaxista mot = mototaxistaServico.buscar(cod);
				if (mot != null) {
					request.setAttribute("mot", mot);
					forward = INSERIR_OU_ALTERAR;
				} else {
					request.setAttribute("lista", mototaxistaServico.buscarTodos());
					forward = LISTAR;
				}
			} catch (RuntimeException e) {
				request.setAttribute("Erro de execu��o: ", e.getMessage());
				forward = ERRO2;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		else if (cmd.equalsIgnoreCase("visualizar")) {
			int cod = Integer.parseInt(request.getParameter("cod"));
			try {
				MotoTaxista mot = mototaxistaServico.buscar(cod);
				if (mot != null) {
					request.setAttribute("mot", mot);
					forward = VISUALIZAR;
				}
			} catch (RuntimeException e) {
				request.setAttribute("Erro de execu��o: ", e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		// Listando m�dia do mototaxista - Apar�cio - UC01
		else if (cmd.equalsIgnoreCase("mediacalc")) {
			int cod = Integer.parseInt(request.getParameter("cod"));

			try {
				MotoTaxista mot = mototaxistaServico.buscar(cod);
				if (mot != null) {
					request.setAttribute("nome", mototaxistaServico.buscar(cod).getNome());
					request.setAttribute("media", mototaxistaServico.buscar(cod).media());
					forward = LISTAR_MEDIA;
				}

			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		else if (cmd.equalsIgnoreCase("escolherEmpresa")) {
			try {
				request.setAttribute("lista", empresaServico.buscarTodos());
				forward = ESCOLHER_EMPRESA;
			} catch (RuntimeException e) {
				request.setAttribute("Erro de execu��o: ", e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		else if (cmd.equalsIgnoreCase("listar")) {
			try {
				request.setAttribute("lista", mototaxistaServico.buscarTodos());
				forward = LISTAR;
			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		// Listar todos os mototaxistas - Apar�cio - UC05
		else if (cmd.equalsIgnoreCase("listar2")) {
			try {
				request.setAttribute("lista", mototaxistaServico.buscarTodos());
				forward = LISTAR_2;
			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		else if (cmd.equalsIgnoreCase("novo")) {
			int codEmpresa = Integer.parseInt(request.getParameter("codEmpresa"));
			try {
				Empresa empr = empresaServico.buscar(codEmpresa);
				MotoTaxista mot = new MotoTaxista(null, null, null, null, empr);

				request.setAttribute("mot", mot);
				forward = INSERIR_OU_ALTERAR;
			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		// Listar todas as empresas - Apar�cio - UC05
		else if (cmd.equalsIgnoreCase("listaemp")) {
			try {
				request.setAttribute("lista", empresaServico.buscarTodos());
				forward = LISTAR_EMPRESA;
			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

		// Listar chamados entre per�odos - Apar�cio - UC05
		else if (cmd.equalsIgnoreCase("chamados")) {
			int cod = Integer.parseInt(request.getParameter("cod"));

			try {
				Empresa empresa = empresaServico.buscar(cod);
				request.setAttribute("cod", empresa.getCodEmpresa());
				forward = FORM_EMPRESA;
			} catch (RuntimeException e) {
				request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
				forward = ERRO;
			}
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		}

	}

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MotoTaxistaServico mototaxistaServico = ServicoFactory.criarMotoTaxistaServico();
		EmpresaServico empService = ServicoFactory.criarEmpresaServico();
		
		String forward = "";
		try {

			MotoTaxista mot = instanciar(request);
			if (mot.getCodMotoTaxista() == null) {
				mototaxistaServico.inserir(mot);
			} else {
				mototaxistaServico.atualizar(mot);
			}
			request.setAttribute("lista", mototaxistaServico.buscarTodos());
			forward = LISTAR;
		} catch (ServicoException s) {
			request.setAttribute("erro", s.getMessage());
			forward = ERRO2;
		} catch (RuntimeException e) {
			request.setAttribute("erro", e.getMessage());
			forward = ERRO2;
		}

		// Listar chamados entre per�odo - Apar�cio - UC05
		String forward1 = "";
		String dataEmTexto = request.getParameter("dataInicial");
		String dataEmTexto2 = request.getParameter("dataFinal");

		try {
			int x = Integer.parseInt(request.getParameter("cod"));
			Empresa teste = empService.buscar(x);
			List<Chamado> lista1 = teste.corridasPorPeriodo(new Date(dataEmTexto), new Date(dataEmTexto2));

			request.setAttribute("lista", lista1);
			forward1 = LISTAR_CHAMADO;

			
		} catch (RuntimeException e) {
			request.setAttribute("erro", "Erro de execu��o: " + e.getMessage());
			forward1 = ERRO;
		
		}
		RequestDispatcher rd = request.getRequestDispatcher(forward1);
		rd.forward(request, response);

	}

	private MotoTaxista instanciar(HttpServletRequest req) throws RuntimeException {
		EmpresaServico empresaServico = ServicoFactory.criarEmpresaServico();

		String aux;
		Boolean aux2;
		MotoTaxista x = new MotoTaxista();
		aux = req.getParameter("codMotoTaxista");

		if (aux != null && !aux.isEmpty())
			x.setCodMotoTaxista(Integer.parseInt(aux));

		aux = req.getParameter("nome");
		x.setNome(aux);

		aux = req.getParameter("placa");
		x.setPlaca(aux);

		aux = req.getParameter("disponivel");
		if (aux.equals("true"))
			aux2 = true;
		else
			aux2 = false;
		x.setDisponivel(aux2);

		aux = req.getParameter("codEmpresa");
		x.setEmpresa(empresaServico.buscar(Integer.parseInt(aux)));

		return x;
	}
}