package tn.kaz.ospas.view.directories.funcrequirement;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import tn.kaz.ospas.view.GuiHelper;
import tn.kaz.ospas.view.directories.funcrequirement.executors.ExecutorTypesTab;
import tn.kaz.ospas.view.directories.funcrequirement.notice.NoticeTab;


/**
 * Created by Anton on 19.01.2017.
 */
public class FuncRequirementTab extends TabSheet {

    public FuncRequirementTab() {
        setSizeFull();
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        setCaption("Функциональные требования");
        addTab(GuiHelper.makeTabContent("Типы исполнителей", Alignment.MIDDLE_CENTER, new ExecutorTypesTab()));
        addTab(GuiHelper.makeTabContent("Типы извещений", Alignment.MIDDLE_CENTER, new NoticeTab()));
        //addTab(GuiHelper.makeTabContent("Структура организации",Alignment.TOP_LEFT, new MainTab()));
    }
}
