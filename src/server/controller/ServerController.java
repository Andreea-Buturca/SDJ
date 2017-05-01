<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.text.Font?>
<AnchorPane xmlns:fx="http://javafx.com/fxml/1" id="AnchorPane" maxHeight="-Infinity" maxWidth="-Infinity"
            minHeight="-Infinity"
            minWidth="-Infinity" prefHeight="600.0" prefWidth="1010.0"
            xmlns="http://javafx.com/javafx/8.0.91" fx:controller="server.controller.Controller">
    <BorderPane onMouseClicked="#changeView" prefHeight="600.0" prefWidth="1010.0">
        <bottom>
            <Label alignment="CENTER_RIGHT" prefWidth="1000.0" text="Copyright Â© 2016 Group1. All rights reserved.">
                <BorderPane.margin>
                    <Insets right="10.0"/>
                </BorderPane.margin>
            </Label>
        </bottom>
        <center>
            <VBox prefHeight="360.0" prefWidth="1000.0">
                <padding>
                    <Insets bottom="20.0" left="10.0" right="10.0"/>
                </padding>
                <Label alignment="CENTER" prefHeight="140.0" prefWidth="1010.0" text="VIA Bus" textAlignment="CENTER">
                    <font>
                        <Font size="100.0"/>
                    </font>
                    <VBox.margin>
                        <Insets bottom="5.0"/>
                    </VBox.margin>
                </Label>
                <Button fx:id="createTour" mnemonicParsing="false" onMouseClicked="#changeView" prefHeight="50.0"
                        prefWidth="1010.0" text="Create tour">
                    <font>
                        <Font size="20.0" fx:id="x1"/>
                    </font>
                    <VBox.margin>
                        <Insets bottom="20.0" top="20.0" fx:id="x2"/>
                    </VBox.margin>
                </Button>
                <Button fx:id="mkReservation" font="$x1" mnemonicParsing="false" onMouseClicked="#changeView"
                        prefHeight="50.0" prefWidth="1010.0" text="Make reservation" VBox.margin="$x2"/>
                <Button fx:id="findTrip" font="$x1" mnemonicParsing="false" onMouseClicked="#changeView"
                        prefHeight="50.0" prefWidth="1010.0" text="Find Reservation / Tour" VBox.margin="$x2"/>
                <Label alignment="CENTER" prefHeight="17.0" prefWidth="1010.0" text="Overview">
                    <font>
                        <Font size="17.0"/>
                    </font>
                </Label>
                <ListView fx:id="tripList" prefHeight="200.0" prefWidth="200.0"/>
            </VBox>
        </center>
        <top>
            <MenuBar fx:id="menu">
                <Menu mnemonicParsing="false" text="Home">
                    <MenuItem fx:id="homeHome" mnemonicParsing="false" onAction="#changeViewMenu" text="Home"/>
                    <MenuItem fx:id="homeTour" mnemonicParsing="false" onAction="#changeViewMenu" text="Create tour"/>
                    <MenuItem fx:id="homeReserve" mnemonicParsing="false" onAction="#changeViewMenu"
                              text="Make reservation"/>
                    <MenuItem fx:id="homeSearch" mnemonicParsing="false" onAction="#changeViewMenu" text="Search"/>
                </Menu>
                <Menu mnemonicParsing="false" text="Bus List">
                    <MenuItem fx:id="homeBus" mnemonicParsing="false" onAction="#changeViewMenu" text="Bus List"/>
                    <MenuItem fx:id="homeBusAdd" mnemonicParsing="false" onAction="#changeViewMenu" text="Add bus"/>
                </Menu>
                <Menu mnemonicParsing="false" text="Chauffeur list">
                    <MenuItem fx:id="homeDriver" mnemonicParsing="false" onAction="#changeViewMenu"
                              text="Chauffeur list"/>
                    <MenuItem fx:id="homeDriverAdd" mnemonicParsing="false" onAction="#changeViewMenu"
                              text="Add chauffeur"/>
                </Menu>
            </MenuBar>
        </top>
    </BorderPane>
</AnchorPane>
