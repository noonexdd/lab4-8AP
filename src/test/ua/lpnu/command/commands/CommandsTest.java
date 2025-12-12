package test.ua.lpnu.command.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.lpnu.command.commands.*;
import ua.lpnu.command.commands.ICommand;
import ua.lpnu.manager.FlowerShopManager;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CommandsTest {
    @Mock
    FlowerShopManager manager;

    @Test
    @DisplayName("AddItemCommand calls addItemToBouquet")
    void addItemCommandTest() {
        ICommand command = new AddItemCommand(manager);
        command.execute();
        verify(manager, times(1)).addItemToBouquet();
    }

    @Test
    @DisplayName("CreateBouquetCommand calls createNewBouquet")
    void createBouquetCommandTest() {
        ICommand command = new CreateBouquetCommand(manager);
        command.execute();
        verify(manager).createNewBouquet();
    }

    @Test
    @DisplayName("RemoveItemCommand calls removeItemFromBouquet")
    void removeItemCommandTest() {
        ICommand command = new RemoveItemCommand(manager);
        command.execute();
        verify(manager).removeItemFromBouquet();
    }

    @Test
    @DisplayName("SortBouquetCommand calls sortCurrentBouquet")
    void sortBouquetCommandTest() {
        ICommand command = new SortBouquetCommand(manager);
        command.execute();
        verify(manager).sortCurrentBouquet();
    }

    @Test
    @DisplayName("FindFlowerCommand calls findInBouquet")
    void findFlowerCommandTest() {
        ICommand command = new FindFlowerCommand(manager);
        command.execute();
        verify(manager).findInBouquet();
    }

    @Test
    @DisplayName("SaveBouquetCommand calls saveBouquetToFile")
    void saveBouquetCommandTest() {
        ICommand command = new SaveBouquetCommand(manager);
        command.execute();
        verify(manager).saveBouquetToFile();
    }

    @Test
    @DisplayName("LoadBouquetCommand calls loadBouquetFromFile")
    void loadBouquetCommandTest() {
        ICommand command = new LoadBouquetCommand(manager);
        command.execute();
        verify(manager).loadBouquetFromFile();
    }

    @Test
    @DisplayName("ShowCatalogCommand calls showCatalog")
    void showCatalogCommandTest() {
        ICommand command = new ShowCatalogCommand(manager);
        command.execute();
        verify(manager).showCatalog();
    }

    @Test
    @DisplayName("GetGuideCommand calls showGuide")
    void getGuideCommandTest() {
        ICommand command = new GetGuideCommand(manager);
        command.execute();
        verify(manager).showGuide();
    }

    @Test
    @DisplayName("SwitchBouquetCommand calls switchBouquet")
    void switchBouquetCommandTest() {
        ICommand command = new SwitchBouquetCommand(manager);
        command.execute();
        verify(manager).switchBouquet();
    }

    @Test
    @DisplayName("ViewBouquetCommand calls showBouquetInfo")
    void viewBouquetCommandTest() {
        ICommand command = new ViewBouquetCommand(manager);
        command.execute();
        verify(manager).showBouquetInfo();
    }

    @Test
    @DisplayName("ReplaceItemCommand calls replaceItem")
    void replaceItemCommandTest() {
        ICommand command = new ReplaceItemCommand(manager);
        command.execute();
        verify(manager).replaceItem();
    }

    @Test
    @DisplayName("ExitCommand calls exit")
    void exitCommandTest() {
        ICommand command = new ExitCommand(manager);
        command.execute();
        verify(manager).exit();
    }

}
