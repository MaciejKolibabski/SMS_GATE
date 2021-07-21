package com.example.sms_gate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



@Route
public class GUI extends VerticalLayout {

    TextField numbertosend;
    TextField messagetosend;
    Button sendbutton;
    Label text1;
    Label text2;

    private ModemCnnt modemCnnt;

    public GUI(ModemCnnt modemCnnt) {
        this.modemCnnt = modemCnnt;
        text1 = new Label("Numer telefonu: ");
        numbertosend = new TextField();
        text2 = new Label("Wiadomość: ");
        messagetosend = new TextField();
        sendbutton = new Button("Send message");
        add(text1,numbertosend, text2,messagetosend,sendbutton);

        sendbutton.addClickListener(buttonClickEvent -> {

            modemCnnt.sendSMS(numbertosend.getValue(), messagetosend.getValue());
        });
    }
}
