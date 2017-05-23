/*
 * The Athena project is an open source dictionary database development tool.
 *
 * Copyright (C) 2017 Ferenc Kretz <ferkretz@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gui;

@SuppressWarnings("serial")
public class SetupWizardFrame extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    public static final int InstallCommand = 0;
    public static final int RecoveryCommand = 1;
    public static final int UnInstallCommand = 2;
    private int command;
    private int installedPanel;
    private javax.swing.JPanel[] panels;
    private util.I18n i18n = new util.I18n(this);
    private final util.I18n i18nError = new util.I18n("ErrorMessages");
    private String welcomeTitleLabelText;
    private String completeTitleLabelText;
    private String configuredLabelText;
    // </editor-fold>

    public SetupWizardFrame() {
        this(InstallCommand);
    }

    public SetupWizardFrame(int command) {
        initComponents();

        // *** not implemented yet ***
        hintButton.setVisible(false);

        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/resource/image/athena_Icon.png")).getImage()); // NOI18N

        this.command = command;
        switch (command) {
            case RecoveryCommand:
                panels = new javax.swing.JPanel[4];
                panels[0] = welcomePanel;
                panels[1] = mySQLGlobalPanel;
                panels[2] = mySQLAthenaPanel;
                panels[3] = setupCompletePanel;
                installedPanel = 0;
                dbDeleteExistedDBCheckBox.setVisible(false);
                welcomeTitleLabelText = "recoveryWelcomeTitleLabel"; // NOI18N
                completeTitleLabelText = "recoveryCompleteTitleLabel"; // NOI18N
                configuredLabelText = "setupCompleteConfiguredLabel"; // NOI18N
                break;
            case UnInstallCommand:
                panels = new javax.swing.JPanel[3];
                panels[0] = welcomePanel;
                panels[1] = mySQLGlobalPanel;
                panels[2] = setupCompletePanel;
                installedPanel = 0;
                dbDriverPanel.setVisible(false);
                dbUrlPrefixPanel.setVisible(false);
                dbHostPanel.setVisible(false);
                dbUseExistedDBCheckBox.setVisible(false);
                dbUseRootUserCheckBox.setVisible(false);
                dbGlobalExpertCheckBox.setVisible(false);
                mySQLGlobalDefaultPanel.setVisible(false);
                setupCompleteRunCheckBox.setVisible(false);
                dbDeleteExistedDBCheckBox.setVisible(true);
                dbRootPassField.setEnabled(false);
                welcomeTitleLabelText = "uninstallWelcomeTitleLabel"; // NOI18N
                completeTitleLabelText = "uninstallCompleteTitleLabel"; // NOI18N
                configuredLabelText = "uninstallCompleteConfiguredLabel"; // NOI18N
                break;
            default:
                panels = new javax.swing.JPanel[5];
                panels[0] = welcomePanel;
                panels[1] = licensePanel;
                panels[2] = mySQLGlobalPanel;
                panels[3] = mySQLAthenaPanel;
                panels[4] = setupCompletePanel;
                installedPanel = 0;
                dbDeleteExistedDBCheckBox.setVisible(false);
                welcomeTitleLabelText = "setupWelcomeTitleLabel"; // NOI18N
                completeTitleLabelText = "setupCompleteTitleLabel"; // NOI18N
                configuredLabelText = "setupCompleteConfiguredLabel"; // NOI18N
        }

        constructPanels();
    }

    private void constructWelcomePanel() {
        if (!"".equals(util.Settings.I18nLanguage.getValue())) { //NOI18N
            java.util.Locale.setDefault(new java.util.Locale(
                    util.Settings.I18nLanguage.getValue(),
                    util.Settings.I18nCountry.getValue()));
        }

        javax.swing.DefaultComboBoxModel<gui.LanguageCountry> countryModel = new javax.swing.DefaultComboBoxModel<>();
        String defaultLC = java.util.Locale.getDefault().toString();
        LanguageCountry defaultCountry = LanguageCountry.valueOf(defaultLC);

        for (LanguageCountry languageCountry : LanguageCountry.values()) {
            countryModel.addElement(languageCountry);

            if (defaultLC.equals(languageCountry.getLC())) {
                defaultCountry = languageCountry;
            }
        }

        javax.swing.ListCellRenderer<gui.LanguageCountry> languageCountryRenderer = new javax.swing.ListCellRenderer<gui.LanguageCountry>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, gui.LanguageCountry languageCountry,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                labelItem.setText(languageCountry.getText());
                labelItem.setIcon(languageCountry.getImageIcon());

                return labelItem;
            }
        };

        countryComboBox.setModel(countryModel);
        countryComboBox.setRenderer(languageCountryRenderer);
        countryComboBox.setSelectedItem(defaultCountry);
    }

    private void constructLicensePanel() {
        try {
            licenseTextPanel.setText("");
            licenseTextPanel.setPage(getClass().getResource(i18n.translate("LicensePath"))); //NOI18N
        } catch (java.io.IOException ex) {
            licenseTextPanel.setText(i18nError.translatef("LoadLicense", i18n.translate("LicensePath"))); //NOI18N
        }
    }

    private void constructMySQLPanels() {
        dbUseExistedDBCheckBox.setSelected(false);
        dbUseRootUserCheckBox.setSelected(false);
        dbGlobalExpertCheckBox.setSelected(false);
        dbDeleteExistedDBCheckBox.setSelected(false);

        dbDriverLabel.setEnabled(false);
        dbDriverField.setEnabled(false);
        dbUrlPrefixLabel.setEnabled(false);
        dbUrlPrefixField.setEnabled(false);

        dbUserPanel.setVisible(true);
        dbPassPanel.setVisible(true);
        dbConfPassPanel.setVisible(true);

        if (command == InstallCommand) {
            dbDriverField.setText(util.Settings.SQLDBDriver.getDefault());
            dbUrlPrefixField.setText(util.Settings.SQLDBUrlPrefix.getDefault());
            dbHostField.setText(util.Settings.SQLDBHost.getDefault());
            dbRootPassField.setText(util.Settings.SQLDBRootPass.getDefault());
            dbNameField.setText(util.Settings.SQLDBName.getDefault());
            dbUserField.setText(util.Settings.SQLDBUser.getDefault());
            dbPassField.setText(util.Settings.SQLDBPass.getDefault());
            dbConfPassField.setText(util.Settings.SQLDBPass.getDefault());
            dbPrefixField.setText(util.Settings.SQLDBPrefix.getDefault());
        } else {
            dbDriverField.setText(util.Settings.SQLDBDriver.getValue());
            dbUrlPrefixField.setText(util.Settings.SQLDBUrlPrefix.getValue());
            dbHostField.setText(util.Settings.SQLDBHost.getValue());
            dbNameField.setText(util.Settings.SQLDBName.getValue());
            dbPrefixField.setText(util.Settings.SQLDBPrefix.getValue());
            dbUserField.setText(util.Settings.SQLDBUser.getValue());
            dbPassField.setText(util.Settings.SQLDBPass.getValue());
            dbConfPassField.setText(util.Settings.SQLDBPass.getValue());
            if ("root".equals(util.Settings.SQLDBUser.getValue())) {
                dbRootPassField.setText(util.Settings.SQLDBPass.getValue());
                dbUseRootUserCheckBox.setSelected(true);
                dbUseRootUserCheckBoxActionPerformed(null);
            }
        }
    }

    private void constructSetupCompletePanel() {
        if (command == UnInstallCommand) {
            setupCompleteRunCheckBox.setSelected(false);
        } else {
            setupCompleteRunCheckBox.setSelected(true);
        }
    }

    private void constructPanels() {
        wizardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/image/athena_Wizard.png"))); // NOI18N

        constructWelcomePanel();
        constructLicensePanel();
        constructMySQLPanels();
        constructSetupCompletePanel();

        setPanel();
    }

    private void updateButtonPanel() {
        hintButton.setText(IconStock.Hint.getText());
        previousButton.setText(IconStock.Previous.getText());
        nextButton.setText(IconStock.Next.getText());
        finishButton.setText(IconStock.Finish.getText());
        cancelButton.setText(IconStock.Cancel.getText());
    }

    private void updateWizardPanel() {
        if (installedPanel == 0) {
            stepLabel.setText(i18n.translate("stepLabel.FirstStep")); //NOI18N
        } else if (installedPanel == panels.length - 1) {
            stepLabel.setText(i18n.translate("stepLabel.LastStep")); //NOI18N
        } else {
            stepLabel.setText(i18n.translatef("stepLabel.StepAway", panels.length - installedPanel - 1)); //NOI18N
        }
    }

    private void updateWelcomePanel() {
        welcomeTitleLabel.setText(i18n.translatef(welcomeTitleLabelText, util.Settings.AppName.getValue()));
        chooseCountryLabel.setText(i18n.translate("chooseCountryLabel")); // NOI18N
    }

    private void updateLicensePanel() {
        licenseTitleLabel.setText(i18n.translate("licenseTitleLabel")); // NOI18N
        acceptLicenseCheckBox.setText(i18n.translate("acceptLicenseCheckBox")); // NOI18N
    }

    private void updateMySQLGlobalPanel() {
        mySQLGlobalTitleLabel.setText(i18n.translate("mySQLGlobalTitleLabel")); // NOI18N
        dbDriverLabel.setText(i18n.translate("dbDriverLabel")); // NOI18N
        dbUrlPrefixLabel.setText(i18n.translate("dbUrlPrefixLabel")); // NOI18N
        dbHostLabel.setText(i18n.translate("dbHostLabel")); // NOI18N
        dbRootPassLabel.setText(i18n.translate("dbRootPassLabel")); // NOI18N
        dbUseExistedDBCheckBox.setText(i18n.translate("dbUseExistedDBCheckBox")); // NOI18N
        dbUseRootUserCheckBox.setText(i18n.translate("dbUseRootUserCheckBox")); // NOI18N
        dbGlobalExpertCheckBox.setText(i18n.translate("dbGlobalExpertCheckBox")); // NOI18N
        mySQLGlobalDefaultButton.setText(IconStock.Defaults.getText());
    }

    private void updateMySQLAthenaPanel() {
        mySQLAthenaTitleLabel.setText(i18n.translatef("mySQLAthenaTitleLabel", util.Settings.AppName.getValue())); // NOI18N
        dbNameLabel.setText(i18n.translate("dbNameLabel")); // NOI18N
        dbUserLabel.setText(i18n.translate("dbUserLabel")); // NOI18N
        dbPassLabel.setText(i18n.translate("dbPassLabel")); // NOI18N
        dbConfPassLabel.setText(i18n.translate("dbConfPassLabel")); // NOI18N
        dbPrefixLabel.setText(i18n.translate("dbPrefixLabel")); // NOI18N
    }

    private void updateSetupCompletePanel() {
        setupCompleteTitleLabel.setText(i18n.translatef(completeTitleLabelText, util.Settings.AppName.getValue())); // NOI18N
        setupCompleteConfiguredLabel.setText(i18n.translatef(configuredLabelText, util.Settings.AppName.getValue())); // NOI18N
        setupCompleteClickFinishLabel.setText(i18n.translate("clickFinishLabel")); // NOI18N
        setupCompleteRunCheckBox.setText(i18n.translatef("setupCompleteRunCheckBox", util.Settings.AppName.getValue())); // NOI18N
    }

    private void updatePanels() {
        setTitle(i18n.translatef("title", util.Settings.AppName.getValue())); // NOI18N

        updateButtonPanel();
        updateWizardPanel();
        updateWelcomePanel();
        updateLicensePanel();
        updateMySQLGlobalPanel();
        updateMySQLAthenaPanel();
        updateSetupCompletePanel();
    }

    private void setPanel() {
        if (contentPanel.getComponentCount() == 2) {
            contentPanel.remove(1);
        }

        if (installedPanel == 0) {
            stepLabel.setText(i18n.translate("stepLabel.FirstStep")); //NOI18N

            previousButton.setEnabled(false);
            nextButton.setEnabled(true);
            finishButton.setEnabled(false);
            cancelButton.setEnabled(true);
        } else if (installedPanel == panels.length - 1) {
            stepLabel.setText(i18n.translate("stepLabel.LastStep")); //NOI18N

            previousButton.setEnabled(false);
            nextButton.setEnabled(false);
            finishButton.setEnabled(true);
            cancelButton.setEnabled(false);
        } else {
            stepLabel.setText(i18n.translatef("stepLabel.StepAway", panels.length - installedPanel - 1)); //NOI18N

            previousButton.setEnabled(true);
            if ("licensePanel".equals(panels[installedPanel].getName())) { //NOI18N
                if (acceptLicenseCheckBox.isSelected()) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            } else {
                nextButton.setEnabled(true);
            }
            finishButton.setEnabled(false);
            cancelButton.setEnabled(true);
        }

        contentPanel.add(panels[installedPanel]);

        pack();
        contentPanel.repaint();
    }

    private void previousPanel() {
        if (!fieldsCheck()) {
            return;
        }

        if (installedPanel > 0) {
            installedPanel--;
            setPanel();
        }
    }

    private void nextPanel() {
        if (!fieldsCheck()) {
            return;
        }

        if (installedPanel < panels.length - 1) {
            if (installedPanel == panels.length - 2) {
                if (setup() == true) {
                    installedPanel++;
                    setPanel();
                }
            } else {
                installedPanel++;
                setPanel();
            }
        }
    }

    private boolean fieldsCheck() {
        if (panels[installedPanel] == mySQLGlobalPanel) {
            if ("".equals(dbDriverField.getText())) {
                dbDriverField.setBackground(java.awt.Color.yellow);
                new MessageDialog(
                        IconStock.DialogError,
                        IconStock.Ok,
                        i18nError.translate("FieldRequired"), //NOI18N
                        this).setVisible(true);
                return false;
            }
            if ("".equals(dbUrlPrefixField.getText())) {
                dbUrlPrefixField.setBackground(java.awt.Color.yellow);
                new MessageDialog(
                        IconStock.DialogError,
                        IconStock.Ok,
                        i18nError.translate("FieldRequired"), //NOI18N
                        this).setVisible(true);
                return false;
            }
            if ("".equals(dbHostField.getText())) {
                dbHostField.setBackground(java.awt.Color.yellow);
                new MessageDialog(
                        IconStock.DialogError,
                        IconStock.Ok,
                        i18nError.translate("FieldRequired"), //NOI18N
                        this).setVisible(true);
                return false;
            }
        }

        if (panels[installedPanel] == mySQLAthenaPanel) {
            if ("".equals(dbNameField.getText())) {
                dbNameField.setBackground(java.awt.Color.yellow);
                new MessageDialog(
                        IconStock.DialogError,
                        IconStock.Ok,
                        i18nError.translate("FieldRequired"), //NOI18N
                        this).setVisible(true);
                return false;
            }
            if (("".equals(dbUserField.getText())) && (dbUseRootUserCheckBox.isSelected() == false)) {
                dbUserField.setBackground(java.awt.Color.yellow);
                new MessageDialog(
                        IconStock.DialogError,
                        IconStock.Ok,
                        i18nError.translate("FieldRequired"), //NOI18N
                        this).setVisible(true);
                return false;
            }
            if ("".equals(dbPrefixField.getText())) {
                dbPrefixField.setBackground(java.awt.Color.yellow);
                new MessageDialog(
                        IconStock.DialogError,
                        IconStock.Ok,
                        i18nError.translate("FieldRequired"), //NOI18N
                        this).setVisible(true);
                return false;
            }
        }

        return true;
    }

    private boolean setup() {
        updateSettings();

        util.SQLDatabase.setRootUser(true);

        if (command == UnInstallCommand) {
            if (dbDeleteExistedDBCheckBox.isSelected()) {
                if (!removeDatabase()) {
                    return false;
                }
            }

            util.Settings.removeFile();

            return true;
        } else {
            if (!passwordTest()) {
                return false;
            }
            if (!databaseExistsTest()) {
                return false;
            }
            if (!createDatabase()) {
                return false;
            }
            if (!saveConfig()) {
                removeDatabase();
                return false;
            }

            return true;
        }
    }

    private void updateSettings() {
        util.Settings.SQLDBDriver.setValue(dbDriverField.getText());
        util.Settings.SQLDBUrlPrefix.setValue(dbUrlPrefixField.getText());
        util.Settings.SQLDBHost.setValue(dbHostField.getText());
        util.Settings.SQLDBName.setValue(dbNameField.getText());
        util.Settings.SQLDBPrefix.setValue(dbPrefixField.getText());
        util.Settings.SQLDBRootPass.setValue(String.valueOf(dbRootPassField.getPassword()));
        if (dbUseRootUserCheckBox.isSelected()) {
            util.Settings.SQLDBUser.setValue("root");
            util.Settings.SQLDBPass.setValue(String.valueOf(dbRootPassField.getPassword()));
        } else {
            util.Settings.SQLDBUser.setValue(dbUserField.getText());
            util.Settings.SQLDBPass.setValue(String.valueOf(dbPassField.getPassword()));
        }
    }

    private boolean passwordTest() {
        if ((!dbUseRootUserCheckBox.isSelected()) && (!dbUseExistedDBCheckBox.isSelected())) {
            if (!String.valueOf(dbPassField.getPassword()).equals(String.valueOf(dbConfPassField.getPassword()))) {
                new MessageDialog(
                        IconStock.DialogError,
                        IconStock.Ok,
                        i18n.translate("PassMatchDialogMsg"), //NOI18N
                        this).setVisible(true);
                return false;
            }
        }

        return true;
    }

    private boolean databaseExistsTest() {
        if (dbUseExistedDBCheckBox.isSelected()) {
            try {
                util.SQLDatabase.setRootUser(dbUseRootUserCheckBox.isSelected());
                if (util.SQLDatabase.isValidDatabase()) {
                    return true;
                } else {
                    new MessageDialog(
                            IconStock.DialogError,
                            IconStock.Ok,
                            i18n.translatef("InvalidDatabaseDialogMsg", util.Settings.SQLDBName.getValue()), //NOI18N
                            this).setVisible(true);
                    return false;
                }
            } catch (java.sql.SQLException ex) {
                new ExceptionDialog(
                        i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                        ex,
                        this).setVisible(true);
                return false;
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                new ExceptionDialog(i18nError.translate("LoadMySQLDriver"), ex, this).setVisible(true); //NOI18N
                return false;
            }
        }

        try {
            if (util.SQLDatabase.databaseExists()) {
                MessageDialog dialog = new MessageDialog(
                        IconStock.DialogWarning,
                        new IconStock[]{IconStock.Yes, IconStock.No},
                        i18n.translatef("DatabaseExistsDialogMsg", util.Settings.SQLDBName.getValue()), //NOI18N
                        this);
                dialog.setVisible(true);

                if (dialog.getReturnStatus() == IconStock.Yes) {
                    util.SQLDatabase.clean();
                    return true;
                }

                return false;
            }
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
            return false;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            new ExceptionDialog(i18nError.translate("LoadMySQLDriver"), ex, this).setVisible(true); //NOI18N
            return false;
        }

        return true;
    }

    private boolean createDatabase() {
        if (dbUseExistedDBCheckBox.isSelected()) {
            return true;
        }

        try {
            util.SQLDatabase.prepare();
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);

            return false;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            new ExceptionDialog(i18nError.translate("LoadMySQLDriver"), ex, this).setVisible(true); //NOI18N

            return false;
        }

        return true;
    }

    private boolean removeDatabase() {
        try {
            util.SQLDatabase.clean();
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
            return false;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            new ExceptionDialog(i18nError.translate("LoadMySQLDriver"), ex, this).setVisible(true); //NOI18N
            return false;
        }

        return true;
    }

    private boolean saveConfig() {
        try {
            try {
                util.SQLDatabase sqlDatabase = new util.SQLDatabase();

                sqlDatabase.updateMemberNameIfExists(util.Settings.MemberId.getIntValue(), util.Settings.MemberName.getStringValue());
                if (util.Settings.MemberId.getIntValue() == 0) {
                    util.Settings.MemberId.setIntValue(sqlDatabase.getLastInsertId());
                }
            } catch (java.sql.SQLException ex) {
                new ExceptionDialog(
                        i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                        ex,
                        this).setVisible(true);
                return false;
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                new ExceptionDialog(i18nError.translate("LoadMySQLDriver"), ex, this).setVisible(true); //NOI18N
                return false;
            }
            util.Settings.saveToFile();
        } catch (java.io.IOException ex) {
            new ExceptionDialog(
                    i18nError.translatef("ConfigSave", util.Settings.ConfigPath.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
            try {
                util.SQLDatabase.clean();
            } catch (java.sql.SQLException ex2) {
                new ExceptionDialog(
                        i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                        ex2,
                        this).setVisible(true);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex2) {
                new ExceptionDialog(i18nError.translate("LoadMySQLDriver"), ex2, this).setVisible(true); //NOI18N
            }

            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomePanel = new javax.swing.JPanel();
        welcomeTitlePanel = new javax.swing.JPanel();
        welcomeTitleLabel = new javax.swing.JLabel();
        welcomeContentPanel = new javax.swing.JPanel();
        chooseCountryLabel = new javax.swing.JLabel();
        countryComboBox = new javax.swing.JComboBox<>();
        licensePanel = new javax.swing.JPanel();
        licenseContentPanel = new javax.swing.JPanel();
        licenseTitleLabel = new javax.swing.JLabel();
        licenseScrollPanel = new javax.swing.JScrollPane();
        licenseTextPanel = new javax.swing.JTextPane();
        acceptLicenseCheckBox = new javax.swing.JCheckBox();
        mySQLGlobalPanel = new javax.swing.JPanel();
        mySQLGlobalFieldsPanel = new javax.swing.JPanel();
        mySQLGlobalTitleLabel = new javax.swing.JLabel();
        dbDriverPanel = new javax.swing.JPanel();
        dbDriverLabel = new javax.swing.JLabel();
        dbDriverField = new javax.swing.JTextField();
        dbUrlPrefixPanel = new javax.swing.JPanel();
        dbUrlPrefixLabel = new javax.swing.JLabel();
        dbUrlPrefixField = new javax.swing.JTextField();
        dbHostPanel = new javax.swing.JPanel();
        dbHostLabel = new javax.swing.JLabel();
        dbHostField = new javax.swing.JTextField();
        dbRootPassPanel = new javax.swing.JPanel();
        dbRootPassLabel = new javax.swing.JLabel();
        dbRootPassField = new javax.swing.JPasswordField();
        dbUseExistedDBCheckBox = new javax.swing.JCheckBox();
        dbUseRootUserCheckBox = new javax.swing.JCheckBox();
        dbGlobalExpertCheckBox = new javax.swing.JCheckBox();
        dbDeleteExistedDBCheckBox = new javax.swing.JCheckBox();
        mySQLGlobalDefaultPanel = new javax.swing.JPanel();
        mySQLGlobalDefaultButton = new Button(IconStock.Defaults);
        mySQLAthenaPanel = new javax.swing.JPanel();
        mySQLAthenaFieldsPanel = new javax.swing.JPanel();
        mySQLAthenaTitleLabel = new javax.swing.JLabel();
        dbNamePanel = new javax.swing.JPanel();
        dbNameLabel = new javax.swing.JLabel();
        dbNameField = new javax.swing.JTextField();
        dbUserPanel = new javax.swing.JPanel();
        dbUserLabel = new javax.swing.JLabel();
        dbUserField = new javax.swing.JTextField();
        dbPassPanel = new javax.swing.JPanel();
        dbPassLabel = new javax.swing.JLabel();
        dbPassField = new javax.swing.JPasswordField();
        dbConfPassPanel = new javax.swing.JPanel();
        dbConfPassLabel = new javax.swing.JLabel();
        dbConfPassField = new javax.swing.JPasswordField();
        dbPrefixPanel = new javax.swing.JPanel();
        dbPrefixLabel = new javax.swing.JLabel();
        dbPrefixField = new javax.swing.JTextField();
        setupCompletePanel = new javax.swing.JPanel();
        setupCompleteTitlePanel = new javax.swing.JPanel();
        setupCompleteTitleLabel = new javax.swing.JLabel();
        setupCompleteContentPanel = new javax.swing.JPanel();
        setupCompleteConfiguredLabel = new javax.swing.JLabel();
        setupCompleteClickFinishLabel = new javax.swing.JLabel();
        setupCompleteRunCheckBox = new javax.swing.JCheckBox();
        contentPanel = new javax.swing.JPanel();
        wizardPanel = new javax.swing.JPanel();
        wizardLabel = new javax.swing.JLabel();
        stepLabel = new javax.swing.JLabel();
        sepPanel = new javax.swing.JPanel();
        separator = new javax.swing.JSeparator();
        buttonPanel = new javax.swing.JPanel();
        helpPanel = new javax.swing.JPanel();
        hintButton = new Button(IconStock.Hint);
        controlPanel = new javax.swing.JPanel();
        previousButton = new Button(IconStock.Previous);
        nextButton = new Button(IconStock.Next);
        finishButton = new Button(IconStock.Finish);
        cancelButton = new Button(IconStock.Cancel);

        welcomePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
        welcomePanel.setMinimumSize(new java.awt.Dimension(475, 49));
        welcomePanel.setPreferredSize(new java.awt.Dimension(475, 266));
        welcomePanel.setLayout(new javax.swing.BoxLayout(welcomePanel, javax.swing.BoxLayout.PAGE_AXIS));

        welcomeTitleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        welcomeTitleLabel.setText(i18n.translatef("recoveryCompleteTitleLabel",util.Settings.AppName.getValue())); // NOI18N
        welcomeTitleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 15, 5));
        welcomeTitlePanel.add(welcomeTitleLabel);

        welcomePanel.add(welcomeTitlePanel);

        chooseCountryLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        chooseCountryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseCountryLabel.setText(i18n.translate("chooseCountryLabel")); // NOI18N
        chooseCountryLabel.setMaximumSize(new java.awt.Dimension(510, 25));
        chooseCountryLabel.setMinimumSize(new java.awt.Dimension(510, 25));
        chooseCountryLabel.setPreferredSize(new java.awt.Dimension(510, 25));
        welcomeContentPanel.add(chooseCountryLabel);

        countryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryComboBoxActionPerformed(evt);
            }
        });
        welcomeContentPanel.add(countryComboBox);

        welcomePanel.add(welcomeContentPanel);

        licensePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        licensePanel.setMinimumSize(new java.awt.Dimension(475, 204));
        licensePanel.setName("licensePanel"); // NOI18N
        licensePanel.setPreferredSize(new java.awt.Dimension(475, 293));

        licenseContentPanel.setMinimumSize(new java.awt.Dimension(465, 230));
        licenseContentPanel.setName(""); // NOI18N
        licenseContentPanel.setPreferredSize(new java.awt.Dimension(465, 272));
        licenseContentPanel.setLayout(new javax.swing.BoxLayout(licenseContentPanel, javax.swing.BoxLayout.PAGE_AXIS));

        licenseTitleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        licenseTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        licenseTitleLabel.setText(i18n.translate("licenseTitleLabel")); // NOI18N
        licenseTitleLabel.setAlignmentX(0.5F);
        licenseTitleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 15, 5));
        licenseTitleLabel.setMaximumSize(new java.awt.Dimension(465, 100));
        licenseTitleLabel.setMinimumSize(new java.awt.Dimension(465, 15));
        licenseContentPanel.add(licenseTitleLabel);

        licenseScrollPanel.setMinimumSize(null);
        licenseScrollPanel.setPreferredSize(new java.awt.Dimension(465, 220));

        licenseTextPanel.setEditable(false);
        licenseTextPanel.setBackground(new java.awt.Color(240, 240, 247));
        licenseTextPanel.setContentType("text/html"); // NOI18N
        licenseTextPanel.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                licenseTextPanelHyperlinkUpdate(evt);
            }
        });
        licenseScrollPanel.setViewportView(licenseTextPanel);

        licenseContentPanel.add(licenseScrollPanel);

        acceptLicenseCheckBox.setText(i18n.translate("acceptLicenseCheckBox")); // NOI18N
        acceptLicenseCheckBox.setAlignmentX(0.5F);
        acceptLicenseCheckBox.setMaximumSize(new java.awt.Dimension(465, 23));
        acceptLicenseCheckBox.setMinimumSize(new java.awt.Dimension(465, 23));
        acceptLicenseCheckBox.setPreferredSize(new java.awt.Dimension(465, 23));
        acceptLicenseCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptLicenseCheckBoxActionPerformed(evt);
            }
        });
        licenseContentPanel.add(acceptLicenseCheckBox);

        licensePanel.add(licenseContentPanel);

        mySQLGlobalPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mySQLGlobalPanel.setMinimumSize(new java.awt.Dimension(475, 204));
        mySQLGlobalPanel.setPreferredSize(new java.awt.Dimension(475, 293));

        mySQLGlobalFieldsPanel.setLayout(new javax.swing.BoxLayout(mySQLGlobalFieldsPanel, javax.swing.BoxLayout.PAGE_AXIS));

        mySQLGlobalTitleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        mySQLGlobalTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mySQLGlobalTitleLabel.setText(i18n.translate("mySQLGlobalTitleLabel")); // NOI18N
        mySQLGlobalTitleLabel.setAlignmentX(0.5F);
        mySQLGlobalTitleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 15, 5));
        mySQLGlobalTitleLabel.setMaximumSize(new java.awt.Dimension(465, 100));
        mySQLGlobalTitleLabel.setMinimumSize(new java.awt.Dimension(465, 15));
        mySQLGlobalFieldsPanel.add(mySQLGlobalTitleLabel);
        mySQLGlobalTitleLabel.getAccessibleContext().setAccessibleName("");

        dbDriverPanel.setLayout(new java.awt.FlowLayout(0));

        dbDriverLabel.setText(i18n.translate("dbDriverLabel")); // NOI18N
        dbDriverLabel.setEnabled(false);
        dbDriverLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbDriverLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbDriverLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbDriverPanel.add(dbDriverLabel);

        dbDriverField.setEnabled(false);
        dbDriverField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbDriverField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbDriverField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dbDriverFieldFocusGained(evt);
            }
        });
        dbDriverPanel.add(dbDriverField);

        mySQLGlobalFieldsPanel.add(dbDriverPanel);

        dbUrlPrefixPanel.setLayout(new java.awt.FlowLayout(0));

        dbUrlPrefixLabel.setText(i18n.translate("dbUrlPrefixLabel")); // NOI18N
        dbUrlPrefixLabel.setEnabled(false);
        dbUrlPrefixLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbUrlPrefixLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbUrlPrefixLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbUrlPrefixPanel.add(dbUrlPrefixLabel);

        dbUrlPrefixField.setEnabled(false);
        dbUrlPrefixField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbUrlPrefixField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbUrlPrefixField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dbUrlPrefixFieldFocusGained(evt);
            }
        });
        dbUrlPrefixPanel.add(dbUrlPrefixField);

        mySQLGlobalFieldsPanel.add(dbUrlPrefixPanel);

        dbHostPanel.setLayout(new java.awt.FlowLayout(0));

        dbHostLabel.setText(i18n.translate("dbHostLabel")); // NOI18N
        dbHostLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbHostLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbHostLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbHostPanel.add(dbHostLabel);

        dbHostField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbHostField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbHostField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dbHostFieldFocusGained(evt);
            }
        });
        dbHostPanel.add(dbHostField);

        mySQLGlobalFieldsPanel.add(dbHostPanel);

        dbRootPassPanel.setLayout(new java.awt.FlowLayout(0));

        dbRootPassLabel.setText(i18n.translate("dbRootPassLabel")); // NOI18N
        dbRootPassLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbRootPassLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbRootPassLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbRootPassPanel.add(dbRootPassLabel);

        dbRootPassField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbRootPassField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbRootPassPanel.add(dbRootPassField);

        mySQLGlobalFieldsPanel.add(dbRootPassPanel);

        dbUseExistedDBCheckBox.setText(i18n.translate("dbUseExistedDBCheckBox")); // NOI18N
        dbUseExistedDBCheckBox.setAlignmentX(0.5F);
        dbUseExistedDBCheckBox.setMaximumSize(new java.awt.Dimension(465, 23));
        dbUseExistedDBCheckBox.setMinimumSize(new java.awt.Dimension(465, 23));
        dbUseExistedDBCheckBox.setPreferredSize(new java.awt.Dimension(465, 23));
        dbUseExistedDBCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbUseExistedDBCheckBoxActionPerformed(evt);
            }
        });
        mySQLGlobalFieldsPanel.add(dbUseExistedDBCheckBox);

        dbUseRootUserCheckBox.setText(i18n.translate("dbUseRootUserCheckBox")); // NOI18N
        dbUseRootUserCheckBox.setAlignmentX(0.5F);
        dbUseRootUserCheckBox.setMaximumSize(new java.awt.Dimension(465, 23));
        dbUseRootUserCheckBox.setMinimumSize(new java.awt.Dimension(465, 23));
        dbUseRootUserCheckBox.setPreferredSize(new java.awt.Dimension(465, 23));
        dbUseRootUserCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbUseRootUserCheckBoxActionPerformed(evt);
            }
        });
        mySQLGlobalFieldsPanel.add(dbUseRootUserCheckBox);

        dbGlobalExpertCheckBox.setText(i18n.translate("dbGlobalExpertCheckBox")); // NOI18N
        dbGlobalExpertCheckBox.setAlignmentX(0.5F);
        dbGlobalExpertCheckBox.setMaximumSize(new java.awt.Dimension(465, 23));
        dbGlobalExpertCheckBox.setMinimumSize(new java.awt.Dimension(465, 23));
        dbGlobalExpertCheckBox.setPreferredSize(new java.awt.Dimension(465, 23));
        dbGlobalExpertCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbGlobalExpertCheckBoxActionPerformed(evt);
            }
        });
        mySQLGlobalFieldsPanel.add(dbGlobalExpertCheckBox);

        dbDeleteExistedDBCheckBox.setText(i18n.translate("dbDeleteExistedDBCheckBox")); // NOI18N
        dbDeleteExistedDBCheckBox.setAlignmentX(0.5F);
        dbDeleteExistedDBCheckBox.setMaximumSize(new java.awt.Dimension(465, 23));
        dbDeleteExistedDBCheckBox.setMinimumSize(new java.awt.Dimension(465, 23));
        dbDeleteExistedDBCheckBox.setPreferredSize(new java.awt.Dimension(465, 23));
        dbDeleteExistedDBCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbDeleteExistedDBCheckBoxActionPerformed(evt);
            }
        });
        mySQLGlobalFieldsPanel.add(dbDeleteExistedDBCheckBox);

        mySQLGlobalDefaultPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mySQLGlobalDefaultPanel.setMinimumSize(new java.awt.Dimension(465, 30));
        mySQLGlobalDefaultPanel.setLayout(new java.awt.FlowLayout(2, 5, 0));

        mySQLGlobalDefaultButton.setActionCommand("previousButton");
        mySQLGlobalDefaultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mySQLGlobalDefaultButtonActionPerformed(evt);
            }
        });
        mySQLGlobalDefaultPanel.add(mySQLGlobalDefaultButton);

        mySQLGlobalFieldsPanel.add(mySQLGlobalDefaultPanel);

        mySQLGlobalPanel.add(mySQLGlobalFieldsPanel);

        mySQLAthenaPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mySQLAthenaPanel.setMinimumSize(new java.awt.Dimension(475, 204));
        mySQLAthenaPanel.setPreferredSize(new java.awt.Dimension(475, 293));

        mySQLAthenaFieldsPanel.setLayout(new javax.swing.BoxLayout(mySQLAthenaFieldsPanel, javax.swing.BoxLayout.PAGE_AXIS));

        mySQLAthenaTitleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        mySQLAthenaTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mySQLAthenaTitleLabel.setText(i18n.translatef("mySQLAthenaTitleLabel",util.Settings.AppName.getValue())); // NOI18N
        mySQLAthenaTitleLabel.setAlignmentX(0.5F);
        mySQLAthenaTitleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 15, 5));
        mySQLAthenaTitleLabel.setMaximumSize(new java.awt.Dimension(465, 100));
        mySQLAthenaTitleLabel.setMinimumSize(new java.awt.Dimension(465, 15));
        mySQLAthenaFieldsPanel.add(mySQLAthenaTitleLabel);

        dbNamePanel.setLayout(new java.awt.FlowLayout(0));

        dbNameLabel.setText(i18n.translate("dbNameLabel")); // NOI18N
        dbNameLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbNameLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbNameLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbNamePanel.add(dbNameLabel);

        dbNameField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbNameField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dbNameFieldFocusGained(evt);
            }
        });
        dbNamePanel.add(dbNameField);

        mySQLAthenaFieldsPanel.add(dbNamePanel);

        dbUserPanel.setLayout(new java.awt.FlowLayout(0));

        dbUserLabel.setText(i18n.translate("dbUserLabel")); // NOI18N
        dbUserLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbUserLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbUserLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbUserPanel.add(dbUserLabel);

        dbUserField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbUserField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbUserField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dbUserFieldFocusGained(evt);
            }
        });
        dbUserPanel.add(dbUserField);

        mySQLAthenaFieldsPanel.add(dbUserPanel);

        dbPassPanel.setLayout(new java.awt.FlowLayout(0));

        dbPassLabel.setText(i18n.translate("dbPassLabel")); // NOI18N
        dbPassLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbPassLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbPassLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbPassPanel.add(dbPassLabel);

        dbPassField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbPassField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbPassPanel.add(dbPassField);

        mySQLAthenaFieldsPanel.add(dbPassPanel);

        dbConfPassPanel.setLayout(new java.awt.FlowLayout(0));

        dbConfPassLabel.setText(i18n.translate("dbConfPassLabel")); // NOI18N
        dbConfPassLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbConfPassLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbConfPassLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbConfPassPanel.add(dbConfPassLabel);

        dbConfPassField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbConfPassField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbConfPassPanel.add(dbConfPassField);

        mySQLAthenaFieldsPanel.add(dbConfPassPanel);

        dbPrefixPanel.setLayout(new java.awt.FlowLayout(0));

        dbPrefixLabel.setText(i18n.translate("dbPrefixLabel")); // NOI18N
        dbPrefixLabel.setMaximumSize(new java.awt.Dimension(150, 15));
        dbPrefixLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        dbPrefixLabel.setPreferredSize(new java.awt.Dimension(150, 15));
        dbPrefixPanel.add(dbPrefixLabel);

        dbPrefixField.setMinimumSize(new java.awt.Dimension(300, 19));
        dbPrefixField.setPreferredSize(new java.awt.Dimension(300, 19));
        dbPrefixField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dbPrefixFieldFocusGained(evt);
            }
        });
        dbPrefixPanel.add(dbPrefixField);

        mySQLAthenaFieldsPanel.add(dbPrefixPanel);

        mySQLAthenaPanel.add(mySQLAthenaFieldsPanel);

        setupCompletePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setupCompletePanel.setMaximumSize(new java.awt.Dimension(475, 32767));
        setupCompletePanel.setMinimumSize(new java.awt.Dimension(475, 49));
        setupCompletePanel.setPreferredSize(new java.awt.Dimension(475, 266));

        setupCompleteTitleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        setupCompleteTitleLabel.setText(i18n.translatef("setupCompleteTitleLabel",util.Settings.AppName.getValue())); // NOI18N
        setupCompleteTitleLabel.setAlignmentX(0.5F);
        setupCompleteTitleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 15, 5));
        setupCompleteTitlePanel.add(setupCompleteTitleLabel);

        setupCompletePanel.add(setupCompleteTitlePanel);

        setupCompleteContentPanel.setMaximumSize(new java.awt.Dimension(465, 200));
        setupCompleteContentPanel.setMinimumSize(new java.awt.Dimension(465, 200));
        setupCompleteContentPanel.setPreferredSize(new java.awt.Dimension(465, 200));
        setupCompleteContentPanel.setLayout(new java.awt.FlowLayout(0, 10, 10));

        setupCompleteConfiguredLabel.setText(i18n.translatef("setupCompleteConfiguredLabel",util.Settings.AppName.getValue())); // NOI18N
        setupCompleteConfiguredLabel.setMaximumSize(new java.awt.Dimension(465, 15));
        setupCompleteConfiguredLabel.setMinimumSize(new java.awt.Dimension(465, 15));
        setupCompleteConfiguredLabel.setPreferredSize(new java.awt.Dimension(465, 15));
        setupCompleteContentPanel.add(setupCompleteConfiguredLabel);

        setupCompleteClickFinishLabel.setText(i18n.translate("clickFinishLabel")); // NOI18N
        setupCompleteClickFinishLabel.setMaximumSize(new java.awt.Dimension(465, 15));
        setupCompleteClickFinishLabel.setMinimumSize(new java.awt.Dimension(465, 15));
        setupCompleteClickFinishLabel.setPreferredSize(new java.awt.Dimension(465, 15));
        setupCompleteContentPanel.add(setupCompleteClickFinishLabel);

        setupCompleteRunCheckBox.setText(i18n.translatef("setupCompleteRunCheckBox",util.Settings.AppName.getValue())); // NOI18N
        setupCompleteRunCheckBox.setMaximumSize(new java.awt.Dimension(465, 80));
        setupCompleteRunCheckBox.setMinimumSize(new java.awt.Dimension(465, 80));
        setupCompleteRunCheckBox.setPreferredSize(new java.awt.Dimension(465, 80));
        setupCompleteRunCheckBox.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        setupCompleteRunCheckBox.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        setupCompleteContentPanel.add(setupCompleteRunCheckBox);

        setupCompletePanel.add(setupCompleteContentPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setName("SetupWizardFrame"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, javax.swing.BoxLayout.LINE_AXIS));

        wizardPanel.setBackground(new java.awt.Color(240, 240, 247));
        wizardPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        wizardPanel.setLayout(new javax.swing.BoxLayout(wizardPanel, javax.swing.BoxLayout.PAGE_AXIS));

        wizardLabel.setAlignmentX(0.5F);
        wizardPanel.add(wizardLabel);

        stepLabel.setAlignmentX(0.5F);
        stepLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 10, 5));
        wizardPanel.add(stepLabel);

        contentPanel.add(wizardPanel);

        getContentPane().add(contentPanel);

        sepPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        sepPanel.setLayout(new javax.swing.BoxLayout(sepPanel, javax.swing.BoxLayout.LINE_AXIS));

        separator.setPreferredSize(null);
        sepPanel.add(separator);

        getContentPane().add(sepPanel);

        buttonPanel.setLayout(new javax.swing.BoxLayout(buttonPanel, javax.swing.BoxLayout.LINE_AXIS));

        helpPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        helpPanel.setLayout(new java.awt.FlowLayout(0, 5, 0));

        hintButton.setActionCommand("hintButton");
        hintButton.setEnabled(false);
        helpPanel.add(hintButton);

        buttonPanel.add(helpPanel);

        controlPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setLayout(new java.awt.FlowLayout(2, 5, 0));

        previousButton.setActionCommand("previousButton");
        previousButton.setEnabled(false);
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });
        controlPanel.add(previousButton);

        nextButton.setActionCommand("nextButton");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        controlPanel.add(nextButton);

        finishButton.setActionCommand("finishButton");
        finishButton.setEnabled(false);
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });
        controlPanel.add(finishButton);

        cancelButton.setActionCommand("cancelButton");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        controlPanel.add(cancelButton);

        buttonPanel.add(controlPanel);

        getContentPane().add(buttonPanel);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-676)/2, (screenSize.height-373)/2, 676, 373);
    }// </editor-fold>//GEN-END:initComponents

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        previousPanel();
    }//GEN-LAST:event_previousButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        nextPanel();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        closeActionPerformed(true);
        if (this.setupCompleteRunCheckBox.isSelected()) {
            new gui.MainEditorFrame().setVisible(true);
        }
    }//GEN-LAST:event_finishButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        closeActionPerformed(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeActionPerformed(false);
    }//GEN-LAST:event_formWindowClosing

    private void dbGlobalExpertCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbGlobalExpertCheckBoxActionPerformed
        if (dbGlobalExpertCheckBox.isSelected()) {
            dbDriverLabel.setEnabled(true);
            dbDriverField.setEnabled(true);
            dbUrlPrefixLabel.setEnabled(true);
            dbUrlPrefixField.setEnabled(true);
        } else {
            dbDriverLabel.setEnabled(false);
            dbDriverField.setEnabled(false);
            dbUrlPrefixLabel.setEnabled(false);
            dbUrlPrefixField.setEnabled(false);
        }
    }//GEN-LAST:event_dbGlobalExpertCheckBoxActionPerformed

    private void countryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryComboBoxActionPerformed
        LanguageCountry languageCountry = (LanguageCountry) countryComboBox.getSelectedItem();

        java.util.Locale.setDefault(languageCountry.getLocale());

        util.Settings.I18nLanguage.setValue(languageCountry.getLanguage());
        util.Settings.I18nCountry.setValue(languageCountry.getCountry());

        i18n = new util.I18n(this);

        updatePanels();
    }//GEN-LAST:event_countryComboBoxActionPerformed

    private void dbUseRootUserCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbUseRootUserCheckBoxActionPerformed
        if (dbUseRootUserCheckBox.isSelected()) {
            dbRootPassLabel.setEnabled(true);
            dbRootPassField.setEnabled(true);

            dbUserPanel.setVisible(false);
            dbPassPanel.setVisible(false);
            dbConfPassPanel.setVisible(false);
        } else {
            dbUserPanel.setVisible(true);
            dbPassPanel.setVisible(true);
            if (!dbUseExistedDBCheckBox.isSelected()) {
                dbConfPassPanel.setVisible(true);
            } else {
                dbRootPassLabel.setEnabled(false);
                dbRootPassField.setEnabled(false);
            }
        }
    }//GEN-LAST:event_dbUseRootUserCheckBoxActionPerformed

    private void mySQLGlobalDefaultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mySQLGlobalDefaultButtonActionPerformed
        if (dbDriverField.getBackground() != java.awt.Color.white) {
            dbDriverField.setBackground(java.awt.Color.white);
        }
        if (dbUrlPrefixField.getBackground() != java.awt.Color.white) {
            dbUrlPrefixField.setBackground(java.awt.Color.white);
        }
        if (dbHostField.getBackground() != java.awt.Color.white) {
            dbHostField.setBackground(java.awt.Color.white);
        }
        if (dbNameField.getBackground() != java.awt.Color.white) {
            dbNameField.setBackground(java.awt.Color.white);
        }
        if (dbUserField.getBackground() != java.awt.Color.white) {
            dbUserField.setBackground(java.awt.Color.white);
        }
        if (dbPrefixField.getBackground() != java.awt.Color.white) {
            dbPrefixField.setBackground(java.awt.Color.white);
        }

        constructMySQLPanels();
    }//GEN-LAST:event_mySQLGlobalDefaultButtonActionPerformed

    private void dbUseExistedDBCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbUseExistedDBCheckBoxActionPerformed
        if (dbUseExistedDBCheckBox.isSelected()) {
            if (!dbUseRootUserCheckBox.isSelected()) {
                dbRootPassLabel.setEnabled(false);
                dbRootPassField.setEnabled(false);
            }

            dbConfPassPanel.setVisible(false);
        } else {
            if (!dbUseRootUserCheckBox.isSelected()) {
                dbRootPassLabel.setEnabled(true);
                dbRootPassField.setEnabled(true);

                dbConfPassPanel.setVisible(true);
            }
        }
    }//GEN-LAST:event_dbUseExistedDBCheckBoxActionPerformed

    private void acceptLicenseCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptLicenseCheckBoxActionPerformed
        if (acceptLicenseCheckBox.isSelected()) {
            nextButton.setEnabled(true);
        } else {
            nextButton.setEnabled(false);
        }
    }//GEN-LAST:event_acceptLicenseCheckBoxActionPerformed

    private void licenseTextPanelHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_licenseTextPanelHyperlinkUpdate
        if (evt.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
            javax.swing.JEditorPane pane = (javax.swing.JEditorPane) evt.getSource();
            if (evt instanceof javax.swing.text.html.HTMLFrameHyperlinkEvent) {
                javax.swing.text.html.HTMLFrameHyperlinkEvent evt2 = (javax.swing.text.html.HTMLFrameHyperlinkEvent) evt;
                javax.swing.text.html.HTMLDocument doc = (javax.swing.text.html.HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt2);
            } else {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("xdg-open " + evt.getURL());
                } catch (java.io.IOException e) {
                    MessageDialog dialog = new MessageDialog(
                            IconStock.DialogError,
                            IconStock.Ok,
                            i18nError.translatef("UrlOpen", evt.getURL()),
                            this);
                    dialog.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_licenseTextPanelHyperlinkUpdate

    private void dbDriverFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dbDriverFieldFocusGained
        if (dbDriverField.getBackground() != java.awt.Color.white) {
            dbDriverField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_dbDriverFieldFocusGained

    private void dbUrlPrefixFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dbUrlPrefixFieldFocusGained
        if (dbUrlPrefixField.getBackground() != java.awt.Color.white) {
            dbUrlPrefixField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_dbUrlPrefixFieldFocusGained

    private void dbHostFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dbHostFieldFocusGained
        if (dbHostField.getBackground() != java.awt.Color.white) {
            dbHostField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_dbHostFieldFocusGained

    private void dbNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dbNameFieldFocusGained
        if (dbNameField.getBackground() != java.awt.Color.white) {
            dbNameField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_dbNameFieldFocusGained

    private void dbUserFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dbUserFieldFocusGained
        if (dbUserField.getBackground() != java.awt.Color.white) {
            dbUserField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_dbUserFieldFocusGained

    private void dbPrefixFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dbPrefixFieldFocusGained
        if (dbPrefixField.getBackground() != java.awt.Color.white) {
            dbPrefixField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_dbPrefixFieldFocusGained

    private void dbDeleteExistedDBCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbDeleteExistedDBCheckBoxActionPerformed
        if (dbDeleteExistedDBCheckBox.isSelected()) {
            dbRootPassField.setEnabled(true);
        } else {
            dbRootPassField.setEnabled(false);
        }
    }//GEN-LAST:event_dbDeleteExistedDBCheckBoxActionPerformed

    private void closeActionPerformed(boolean finish) {
        if (installedPanel == panels.length - 1) {
            finish = true;
        }
        if (finish == true) {
            doClose();
        } else {
            MessageDialog abortDialog = new MessageDialog(
                    IconStock.DialogWarning,
                    new IconStock[]{IconStock.Yes, IconStock.No},
                    i18n.translate("AbortConfirmMsg"),
                    this);
            abortDialog.setVisible(true);

            if (abortDialog.getReturnStatus() == IconStock.Yes) {
                doClose();
            }
        }
    }

    private void doClose() {
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox acceptLicenseCheckBox;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel chooseCountryLabel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JComboBox<gui.LanguageCountry> countryComboBox;
    private javax.swing.JPasswordField dbConfPassField;
    private javax.swing.JLabel dbConfPassLabel;
    private javax.swing.JPanel dbConfPassPanel;
    private javax.swing.JCheckBox dbDeleteExistedDBCheckBox;
    private javax.swing.JTextField dbDriverField;
    private javax.swing.JLabel dbDriverLabel;
    private javax.swing.JPanel dbDriverPanel;
    private javax.swing.JCheckBox dbGlobalExpertCheckBox;
    private javax.swing.JTextField dbHostField;
    private javax.swing.JLabel dbHostLabel;
    private javax.swing.JPanel dbHostPanel;
    private javax.swing.JTextField dbNameField;
    private javax.swing.JLabel dbNameLabel;
    private javax.swing.JPanel dbNamePanel;
    private javax.swing.JPasswordField dbPassField;
    private javax.swing.JLabel dbPassLabel;
    private javax.swing.JPanel dbPassPanel;
    private javax.swing.JTextField dbPrefixField;
    private javax.swing.JLabel dbPrefixLabel;
    private javax.swing.JPanel dbPrefixPanel;
    private javax.swing.JPasswordField dbRootPassField;
    private javax.swing.JLabel dbRootPassLabel;
    private javax.swing.JPanel dbRootPassPanel;
    private javax.swing.JTextField dbUrlPrefixField;
    private javax.swing.JLabel dbUrlPrefixLabel;
    private javax.swing.JPanel dbUrlPrefixPanel;
    private javax.swing.JCheckBox dbUseExistedDBCheckBox;
    private javax.swing.JCheckBox dbUseRootUserCheckBox;
    private javax.swing.JTextField dbUserField;
    private javax.swing.JLabel dbUserLabel;
    private javax.swing.JPanel dbUserPanel;
    private javax.swing.JButton finishButton;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JButton hintButton;
    private javax.swing.JPanel licenseContentPanel;
    private javax.swing.JPanel licensePanel;
    private javax.swing.JScrollPane licenseScrollPanel;
    private javax.swing.JTextPane licenseTextPanel;
    private javax.swing.JLabel licenseTitleLabel;
    private javax.swing.JPanel mySQLAthenaFieldsPanel;
    private javax.swing.JPanel mySQLAthenaPanel;
    private javax.swing.JLabel mySQLAthenaTitleLabel;
    private javax.swing.JButton mySQLGlobalDefaultButton;
    private javax.swing.JPanel mySQLGlobalDefaultPanel;
    private javax.swing.JPanel mySQLGlobalFieldsPanel;
    private javax.swing.JPanel mySQLGlobalPanel;
    private javax.swing.JLabel mySQLGlobalTitleLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JPanel sepPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel setupCompleteClickFinishLabel;
    private javax.swing.JLabel setupCompleteConfiguredLabel;
    private javax.swing.JPanel setupCompleteContentPanel;
    private javax.swing.JPanel setupCompletePanel;
    private javax.swing.JCheckBox setupCompleteRunCheckBox;
    private javax.swing.JLabel setupCompleteTitleLabel;
    private javax.swing.JPanel setupCompleteTitlePanel;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JPanel welcomeContentPanel;
    private javax.swing.JPanel welcomePanel;
    private javax.swing.JLabel welcomeTitleLabel;
    private javax.swing.JPanel welcomeTitlePanel;
    private javax.swing.JLabel wizardLabel;
    private javax.swing.JPanel wizardPanel;
    // End of variables declaration//GEN-END:variables
}
