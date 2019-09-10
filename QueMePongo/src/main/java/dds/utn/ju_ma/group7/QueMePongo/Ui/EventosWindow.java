package dds.utn.ju_ma.group7.QueMePongo.Ui;

import java.awt.Color;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.MainWindow;

public class EventosWindow extends MainWindow<EventosViewModel>{

	private static final long serialVersionUID = 1L;

	public EventosWindow() {
		super(new EventosViewModel());
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
		table.bindItemsToProperty("eventos");
		
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
		new EventosWindow().startApplication();
	}

}
