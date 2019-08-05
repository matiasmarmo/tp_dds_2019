package dds.utn.ju_ma.group7.QueMePongo.Ui;

import java.awt.Color;
import java.awt.TextField;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MainWindow;

import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public class EventoWindow extends MainWindow<ViewModel>{

	public EventoWindow() {
		super(new ViewModel());
	}
	
	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("Window");
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel)
			.setText("Ingrese la primer fecha \"YYYY-MM-DD\"")
			.setBackground(Color.ORANGE);
		
		new TextBox(mainPanel)
	    	.setWidth(250)
	    	.bindValueToProperty("fechaInicio");
		
		new Label(mainPanel)
			.setText("Ingrese la segunda fecha \"YYYY-MM-DD\"")
			.setBackground(Color.ORANGE);
		
		new TextBox(mainPanel)
    		.setWidth(250)
    		.bindValueToProperty("fechaFin");

		new Button(mainPanel)
			.setCaption("Buscar eventos")
			.onClick(()-> this.getModelObject().cargarEventos());
		
		Table<EventoObservable> table = new Table<EventoObservable>(mainPanel, EventoObservable.class);
		table.setNumberVisibleRows(10);
		table.bindItemsToProperty("a");
		
		new Column<EventoObservable>(table)
    	.setTitle("Fecha")
    	.setFixedSize(150)
    	.bindContentsToProperty("fecha");
		new Column<EventoObservable>(table)
	    	.setTitle("Descripcion")
	    	.setFixedSize(150)
	    	.bindContentsToProperty("descripcion");
		new Column<EventoObservable>(table)
    		.setTitle("Fue sugerido")
    		.setFixedSize(150)
    		.bindContentsToProperty("fueSugerido");
	}
	
	public static void main(String[] args) {
		Calendar plusCincoDias = Calendar.getInstance();
		plusCincoDias.add(Calendar.DATE, 5);
		Calendar plusDiezDias = Calendar.getInstance();
		plusDiezDias.add(Calendar.DATE, 10);
		List<Sugerencia> sugerencias = Arrays.asList(new Sugerencia(null));
		RepositorioEventos.instanciarEventoUnico(
				new UsuarioPremium(Arrays.asList()), null, Calendar.getInstance(), "Evento 1")
				.serSugerido(sugerencias);
		RepositorioEventos.instanciarEventoUnico(null, null, Calendar.getInstance(), "Evento 2");
		RepositorioEventos.instanciarEventoUnico(null, null, plusCincoDias, "Evento 3");
		RepositorioEventos.instanciarEventoUnico(null, null, plusDiezDias, "Evento 4");	
		
		new EventoWindow().startApplication();
	}

}
