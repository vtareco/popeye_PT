package net.dms.fsync.swing.Panes;

import net.dms.fsync.synchronizer.LocalVariables.control.LocalVariables;
import net.dms.fsync.synchronizer.LocalVariables.entities.Filter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class FilterManagePane extends JPanel {

    private JList filters;
    private JScrollPane scroll;
    private JTextField filterNameJtf;
    private JTextField filterQueryJtf;

    private JButton saveJBtn, deleteFilterJBtn, newFiltrJBtn;


    public FilterManagePane(String path) {
        setLayout(null);
        this.setSize(600, 400);
        this.setName("ApplicationProperties");
        loadPane(path);
    }


    public void loadPane(String path) {
        LocalVariables lv = new LocalVariables();
        saveJBtn = new JButton();
        deleteFilterJBtn = new JButton();
        newFiltrJBtn = new JButton();
        ArrayList<Filter> arFilter = lv.filterList(path);
        scroll = new JScrollPane();
        filters = new JList<Filter>();
        filterNameJtf = new JTextField();
        filterQueryJtf = new JTextField();


        buttonConfigurationLoad();
        textFieldConfigurationLoader();
        jlistConfigurationLoad(path);

        filters.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    loadTextIntoJTFS(filters.getSelectedValue().toString(), path);
                }
            }
        });

        saveJBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!filterQueryJtf.getText().isEmpty() && !filterNameJtf.getText().isEmpty()) {
                    filterControl(filterNameJtf.getText(),filterQueryJtf.getText(),path);
                    textFieldConfigurationLoader();
                    jlistConfigurationLoad(path);
                    filters.removeAll();
                }

            }
        });

        deleteFilterJBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!filterQueryJtf.getText().isEmpty() && !filterNameJtf.getText().isEmpty()) {
                    filterDelete(filterNameJtf.getText(),filterQueryJtf.getText(),path);
                    textFieldConfigurationLoader();
                    jlistConfigurationLoad(path);
                    filters.removeAll();
                }
            }
        });

    }

    private void filterDelete(String filterName, String filterQuery, String path) {
        LocalVariables lv = new LocalVariables();
        Filter filter = new Filter(filterName, filterQuery);
        lv.filterDelete(filter, path);
    }


    private void filterControl(String filterName, String filterQuery, String path) {
        LocalVariables lv = new LocalVariables();
        Filter filter = new Filter(filterName, filterQuery);
        lv.filterConfiguration(filter, path);
    }


    private void loadTextIntoJTFS(String fName, String path) {
        LocalVariables lv = new LocalVariables();
        ArrayList<Filter> arFilter = lv.filterList(path);
        for (Filter f : arFilter) {
            if (fName.equals(f.getFilterName())) {
                filterNameJtf.setText(f.getFilterName());
                filterQueryJtf.setText(f.getFilterQuery());
                break;
            }
        }
    }

    private void jlistConfigurationLoad(String path) {
        LocalVariables lv = new LocalVariables();
        ArrayList<Filter> arFilter = lv.filterList(path);
        DefaultListModel model = new DefaultListModel();
        for (Filter f : arFilter) {
            model.addElement(f.getFilterName());
            filters.setModel(model);

        }

        filters.setBounds(20, 20, 250, 300);
        filters.setFont(new Font("Arial", Font.PLAIN, 16));
        filters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        this.add(filters);
    }

    private void textFieldConfigurationLoader() {

        filterNameJtf.setBounds(300, 80, 250, 25);
        filterNameJtf.setFont(new Font("Arial", Font.PLAIN, 16));
        filterNameJtf.setText(null);
        this.add(filterNameJtf);

        filterQueryJtf.setBounds(300, 110, 250, 25);
        filterQueryJtf.setFont(new Font("Arial", Font.PLAIN, 16));
        filterQueryJtf.setText(null);
        this.add(filterQueryJtf);

    }

    private void buttonConfigurationLoad() {


        saveJBtn.setBounds(300, 160, 80, 20);
        saveJBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        saveJBtn.setText("Save");
        this.add(saveJBtn);

        deleteFilterJBtn.setBounds(470, 160, 80, 20);
        deleteFilterJBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        deleteFilterJBtn.setText("Delete");
        this.add(deleteFilterJBtn);
    }

}