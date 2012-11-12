package mx.com.apestudio.gwt.eltorneo.client.modules;

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

public class ManagementModule extends VLayout implements ClickHandler,
		RecordClickHandler, OpenModuleRequestedHandler {

	private ListGrid grdModules;
	private EventBus eventBus;
	private TabSet modulesTabSet;

	public ManagementModule(EventBus eventBus) {
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
				"Management");
		grdModules = new ListGrid();
		grdModules.setAutoFetchData(true);
		grdModules.setDataSource(new RestDataSource() {
			{
				setDataURL("api/rest/modules");
				setDataFormat(DSDataFormat.JSON);

				DataSourceField fldId = new DataSourceField("id",
						FieldType.INTEGER);
				fldId.setPrimaryKey(true);
				fldId.setHidden(true);
				DataSourceField fldNombre = new DataSourceField("name",
						FieldType.TEXT, "Module");

				setFields(fldId, fldNombre);
			}
		});
		grdModules.addClickHandler(this);
		grdModules.addRecordClickHandler(this);
		admonStackSection.setItems(grdModules);
		leftStack.addSection(admonStackSection);
		torneoTab.setPane(leftStack);
		torneoTabSet.addTab(torneoTab);
		leftPanel.setMembers(torneoTabSet);
		modulesTabSet = new TabSet();
		container.setMembers(leftPanel, modulesTabSet);
		setMembers(header, container);
	}

	@Override
	public void onClick(ClickEvent event) {
		Widget source = (Widget) event.getSource();
		if (grdModules == source) {

		}
	}

	@Override
	public void onRecordClick(RecordClickEvent event) {
		Widget source = (Widget) event.getSource();
		if(grdModules == source){
			String module = event.getRecord().getAttribute("name");
			eventBus.fireEvent(new OpenModuleRequestedEvent(module));
		}
	}

	@Override
	public void onModuleRequested(OpenModuleRequestedEvent event) {
		Canvas module;
		Tab tab = modulesTabSet.getTab(event.getModuleName());
		if(tab != null){
			modulesTabSet.selectTab(tab);
			return;
		}
		if("Team Registration".equals(event.getModuleName())){
			module = new TeamRegistrationModule();
			tab = new Tab(event.getModuleName());
			tab.setPane(module);
		}
		if(tab != null){
			modulesTabSet.addTab(tab);
		}
	}
}
