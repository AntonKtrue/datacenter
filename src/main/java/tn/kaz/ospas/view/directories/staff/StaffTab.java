package tn.kaz.ospas.view.directories.staff;

import tn.kaz.ospas.view.GuiHelper;
import tn.kaz.ospas.view.directories.staff.ranks.RankTab;
import tn.kaz.ospas.view.directories.staff.structure.MainTab;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Структура предпрятия и сотрудники
 *
 * 19.01.2017 произведен легкий рефакторинг - вынос кнопок окна добалвения
 * TODO:
 * редактирование записей
 * форматирование GUI
 */
public class StaffTab extends TabSheet {

    public StaffTab() {
        setSizeFull();
        addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        setCaption("Сотрудники");
        addTab(GuiHelper.makeTabContent("Должности", Alignment.MIDDLE_CENTER, new RankTab()));
        addTab(GuiHelper.makeTabContent("Структура организации",Alignment.TOP_LEFT, new MainTab()));
    }
}
