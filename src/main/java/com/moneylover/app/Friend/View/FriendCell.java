package com.moneylover.app.Friend.View;

import com.moneylover.Infrastructure.Contracts.DialogInterface;
import com.moneylover.Modules.Friend.Controllers.FriendController;
import com.moneylover.Modules.Friend.Entities.Friend;
import com.moneylover.app.Friend.FriendPresenter;
import com.moneylover.app.User.UserPresenter;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.SQLException;

public class FriendCell extends ListCell<Friend> implements DialogInterface {
    private HBox friendCell;

    private Friend friend;

    private StringProperty handledFriendId;

    public FriendCell(StringProperty handledFriendId) throws IOException {
        this.handledFriendId = handledFriendId;
        this._loadCell();
    }

    private void _loadCell() throws IOException {
        FXMLLoader friendCellLoader = new FXMLLoader(
                getClass().getResource("/com/moneylover/pages/friend/friend-cell.fxml")
        );
        friendCellLoader.setController(this);
        this.friendCell = friendCellLoader.load();
    }

    /*========================== Draw ==========================*/
    @FXML
    private Label labelFriendName;

    @FXML
    private TextField textFieldFriendName;

    @Override
    protected void updateItem(Friend item, boolean empty) {
        super.updateItem(item, empty);
        this.friend = item;

        if (empty) {
            setGraphic(null);
            return;
        }

        this.labelFriendName.setText(item.getName());
        setGraphic(this.friendCell);
    }

    @FXML
    private void showPopup(Event e) throws IOException {
        this.addEditPopup((Node) e.getSource());
    }

    @FXML
    private void edit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/moneylover/components/dialogs/friend/friend-save.fxml")
        );
        fxmlLoader.setController(this);
        Parent parent = fxmlLoader.load();
        this.textFieldFriendName.setText(this.friend.getName());
        this.createScreen(parent, "Edit Friend", 300, 120);
    }

    @FXML
    private void saveFriend(Event event) {
        String name = this.textFieldFriendName.getText();
        String validation = FriendPresenter.validateFriend(name);

        if (validation != null) {
            this.showErrorDialog(validation);
            return;
        }

        Friend friend = new Friend(UserPresenter.getUser().getId(), name);
        try {
            int id = this.friend.getId();
            (new FriendController()).update(friend, id);
            this.handledFriendId.set(null);
            this.handledFriendId.set("UPDATE-" + id);
            this.closeScene(event);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            this.showErrorDialog("An error has occurred");
        }
    }

    @FXML
    private void delete() {
        ButtonBar.ButtonData buttonData = this.showDeleteDialog();
        if (buttonData == ButtonBar.ButtonData.YES) {
            try {
                int id = this.friend.getId();
                (new FriendController()).delete(id);
                this.handledFriendId.set("DELETE-" + id);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                this.showErrorDialog("An error has occurred");
            }
        }
    }

    @FXML
    public void closeScene(Event e) {
        DialogInterface.closeScene(e);
    }
}
