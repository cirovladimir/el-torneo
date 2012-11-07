package mx.com.apestudio.gwt.eltorneo.client.modulos;

import mx.com.apestudio.gwt.eltorneo.client.events.OpenModuleRequestedEvent;
import mx.com.apestudio.gwt.eltorneo.client.events.OpenModuleRequestedHandler;

import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class ModuloAdministracion extends VLayout implements ClickHandler,
		RecordClickHandler, OpenModuleRequestedHandler {

	private ListGrid grdModulosAdmon;
	private EventBus eventBus;
	private TabSet modulosTabSet;

	public ModuloAdministracion(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.addHandler(OpenModuleRequestedEvent.TYPE, this);
		setSize("100%", "100%");
		Canvas header = new HLayout();
		header.setHeight(120);
		header.setStyleName("torneo-header");
		HLayout container = new HLayout();
		VLayout leftPanel = new VLayout();
		leftPanel.setShowResizeBar(true);
		leftPanel.setWidth(250);
		TabSet torneoTabSet = new TabSet();
		Tab torneoTab = new Tab("Torneo");
		SectionStack leftStack = new SectionStack();
		SectionStackSection admonStackSection = new SectionStackSection(
				"Administración");
		grdModulosAdmon = new ListGrid();
		grdModulosAdmon.setAutoFetchData(true);
		grdModulosAdmon.setDataSource(new RestDataSource() {
			{
				setDataURL("api/rest/modulos");
				setDataFormat(DSDataFormat.JSON);

				DataSourceField fldId = new DataSourceField("id",
						FieldType.INTEGER);
				fldId.setPrimaryKey(true);
				fldId.setHidden(true);
				DataSourceField fldNombre = new DataSourceField("nombre",
						FieldType.TEXT, "Modulo");

				setFields(fldId, fldNombre);
			}
		});
		grdModulosAdmon.addClickHandler(this);
		grdModulosAdmon.addRecordClickHandler(this);
		admonStackSection.setItems(grdModulosAdmon);
		leftStack.addSection(admonStackSection);
		torneoTab.setPane(leftStack);
		torneoTabSet.addTab(torneoTab);
		leftPanel.setMembers(torneoTabSet);
		modulosTabSet = new TabSet();
		container.setMembers(leftPanel, modulosTabSet);
		setMembers(header, container);
	}

	@Override
	public void onClick(ClickEvent event) {
		Widget source = (Widget) event.getSource();
		if (grdModulosAdmon == source) {

		}
	}

	@Override
	public void onRecordClick(RecordClickEvent event) {
		Widget source = (Widget) event.getSource();
		if(grdModulosAdmon == source){
			String modulo = event.getRecord().getAttribute("nombre");
			eventBus.fireEvent(new OpenModuleRequestedEvent(modulo));
		}
	}

	@Override
	public void onModuleRequested(OpenModuleRequestedEvent event) {
		Canvas modulo;
		Tab tab = modulosTabSet.getTab(event.getModuleName());
		if(tab != null){
			modulosTabSet.selectTab(tab);
			return;
		}
		if("RegistroEquipo".equals(event.getModuleName())){
			modulo = new ModuloRegistroEquipo();
			tab = new Tab(event.getModuleName());
			tab.setPane(modulo);
		}
		if(tab != null){
			modulosTabSet.addTab(tab);
		}
	}
}
