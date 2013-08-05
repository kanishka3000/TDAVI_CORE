
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 * This is a JPanel subclass which provides a special functionality
 * for its children buttons components.
 * It makes it possible to transfer focus from button to button
 * with help of arrows keys.
 * <p>The following example shows how to enable cyclic focus transfer
 * <pre>
 * import org.jdesktop.swinghelper.buttonpanel.*;
 * import javax.swing.*;
 *
 * public class SimpleDemo {
 *     public static void main(String[] args) throws Exception {
 *         SwingUtilities.invokeLater(new Runnable() {
 *             public void run() {
 *                 final JFrame frame = new JFrame();
 *                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 *
 *                 JXButtonPanel panel = new JXButtonPanel();
 *                 panel.setCyclic(true);
 *
 *                 panel.add(new JButton("One"));
 *                 panel.add(new JButton("Two"));
 *                 panel.add(new JButton("Three"));
 *
 *                 frame.add(panel);
 *                 frame.setSize(200, 200);
 *                 frame.setLocationRelativeTo(null);
 *                 frame.setVisible(true);
 *             }
 *         });
 *     }
 * }
 * </pre>
 *
 * If your buttons inside JXButtonPanel are added to one ButtonGroup
 * arrow keys will transfer selection between them as well as they do it for focus<p>
 * Note: you can control this behaviour with setGroupSelectionFollowFocus(boolean)
 * <pre>
 * import org.jdesktop.swinghelper.buttonpanel.*;
 * import javax.swing.*;
 *
 * public class RadioButtonDemo {
 *     public static void main(String[] args) throws Exception {
 *         SwingUtilities.invokeLater(new Runnable() {
 *             public void run() {
 *                 final JFrame frame = new JFrame();
 *                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 *
 *                 JXButtonPanel panel = new JXButtonPanel();
 *                 ButtonGroup group = new ButtonGroup();
 *
 *                 JRadioButton rb1 = new JRadioButton("One");
 *                 panel.add(rb1);
 *                 group.add(rb1);
 *                 JRadioButton rb2 = new JRadioButton("Two");
 *                 panel.add(rb2);
 *                 group.add(rb2);
 *                 JRadioButton rb3 = new JRadioButton("Three");
 *                 panel.add(rb3);
 *                 group.add(rb3);
 *
 *                 rb1.setSelected(true);
 *                 frame.add(panel);
 *
 *                 frame.setSize(200, 200);
 *                 frame.setLocationRelativeTo(null);
 *                 frame.setVisible(true);
 *             }
 *         });
 *     }
 * }
 * </pre>
 *
 * @author Alexander Potochkin
 *
 * https://swinghelper.dev.java.net/
 * http://weblogs.java.net/blog/alexfromsun/
 */
class JXButtonPanel extends JPanel {

    private boolean isCyclic;
    private boolean isGroupSelectionFollowFocus;

    /**
     * {@inheritDoc}
     */
    public JXButtonPanel() {
        super();
        init();
    }

    /**
     * {@inheritDoc}
     */
    public JXButtonPanel(LayoutManager layout) {
        super(layout);
        init();
    }

    /**
     * {@inheritDoc}
     */
    public JXButtonPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        init();
    }

    /**
     * {@inheritDoc}
     */
    public JXButtonPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        init();
    }

    private void init() {
        setFocusTraversalPolicyProvider(true);
        setFocusTraversalPolicy(new JXButtonPanelFocusTraversalPolicy());
        ActionListener actionHandler = new ActionHandler();
        registerKeyboardAction(actionHandler, ActionHandler.FORWARD,
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        registerKeyboardAction(actionHandler, ActionHandler.FORWARD,
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        registerKeyboardAction(actionHandler, ActionHandler.BACKWARD,
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        registerKeyboardAction(actionHandler, ActionHandler.BACKWARD,
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setGroupSelectionFollowFocus(true);
    }

    /**
     * Returns whether arrow keys should support
     * cyclic focus traversal ordering for for this JXButtonPanel.
     */
    public boolean isCyclic() {
        return isCyclic;
    }

    /**
     * Sets whether arrow keys should support
     * cyclic focus traversal ordering for this JXButtonPanel.
     */
    public void setCyclic(boolean isCyclic) {
        this.isCyclic = isCyclic;
    }

    /**
     * Returns whether arrow keys should transfer button's
     * selection as well as focus for this JXButtonPanel.<p>
     *
     * Note: this property affects buttons which are added to a ButtonGroup
     */
    public boolean isGroupSelectionFollowFocus() {
        return isGroupSelectionFollowFocus;
    }

    /**
     * Sets whether arrow keys should transfer button's
     * selection as well as focus for this JXButtonPanel.<p>
     *
     * Note: this property affects buttons which are added to a ButtonGroup
     */
    public void setGroupSelectionFollowFocus(boolean groupSelectionFollowFocus) {
        isGroupSelectionFollowFocus = groupSelectionFollowFocus;
    }

    private static ButtonGroup getButtonGroup(AbstractButton button) {
        ButtonModel model = button.getModel();
        if (model instanceof DefaultButtonModel) {
            return ((DefaultButtonModel) model).getGroup();
        }
        return null;
    }

    private class ActionHandler implements ActionListener {

        private static final String FORWARD = "moveSelectionForward";
        private static final String BACKWARD = "moveSelectionBackward";

        public void actionPerformed(ActionEvent e) {
            FocusTraversalPolicy ftp = JXButtonPanel.this.getFocusTraversalPolicy();

            if (ftp instanceof JXButtonPanelFocusTraversalPolicy) {
                JXButtonPanelFocusTraversalPolicy xftp =
                        (JXButtonPanelFocusTraversalPolicy) ftp;

                String actionCommand = e.getActionCommand();
                Component fo =
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                Component next;

                xftp.setAlternativeFocusMode(true);

                if (FORWARD.equals(actionCommand)) {
                    next = xftp.getComponentAfter(JXButtonPanel.this, fo);
                } else if (BACKWARD.equals(actionCommand)) {
                    next = xftp.getComponentBefore(JXButtonPanel.this, fo);
                } else {
                    throw new AssertionError("Unexpected action command: " + actionCommand);
                }

                xftp.setAlternativeFocusMode(false);

                if (fo instanceof AbstractButton) {
                    AbstractButton b = (AbstractButton) fo;
                    b.getModel().setPressed(false);
                }
                if (next != null) {
                    if (fo instanceof AbstractButton && next instanceof AbstractButton) {
                        ButtonGroup group = getButtonGroup((AbstractButton) fo);
                        AbstractButton nextButton = (AbstractButton) next;
                        if (group != getButtonGroup(nextButton)) {
                            return;
                        }
                        if (isGroupSelectionFollowFocus() && group != null &&
                                group.getSelection() != null && !nextButton.isSelected()) {
                            nextButton.setSelected(true);
                        }
                        next.requestFocusInWindow();
                    }
                }
            }
        }
    }

    private class JXButtonPanelFocusTraversalPolicy extends LayoutFocusTraversalPolicy {

        private boolean isAlternativeFocusMode;

        public boolean isAlternativeFocusMode() {
            return isAlternativeFocusMode;
        }

        public void setAlternativeFocusMode(boolean alternativeFocusMode) {
            isAlternativeFocusMode = alternativeFocusMode;
        }

        protected boolean accept(Component c) {
            if (!isAlternativeFocusMode() && c instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) c;
                ButtonGroup group = JXButtonPanel.getButtonGroup(button);
                if (group != null && group.getSelection() != null && !button.isSelected()) {
                    return false;
                }
            }
            return super.accept(c);
        }

        public Component getComponentAfter(Container aContainer, Component aComponent) {
            Component componentAfter = super.getComponentAfter(aContainer, aComponent);
            if (!isAlternativeFocusMode()) {
                return componentAfter;
            }
            if (JXButtonPanel.this.isCyclic()) {
                return componentAfter == null ? getFirstComponent(aContainer) : componentAfter;
            }
            if (aComponent == getLastComponent(aContainer)) {
                return aComponent;
            }
            return componentAfter;
        }

        public Component getComponentBefore(Container aContainer, Component aComponent) {
            Component componentBefore = super.getComponentBefore(aContainer, aComponent);
            if (!isAlternativeFocusMode()) {
                return componentBefore;
            }
            if (JXButtonPanel.this.isCyclic()) {
                return componentBefore == null ? getLastComponent(aContainer) : componentBefore;
            }
            if (aComponent == getFirstComponent(aContainer)) {
                return aComponent;
            }
            return componentBefore;
        }
    }
}

/**
 * @author Alexander Potochkin
 *
 * https://swinghelper.dev.java.net/
 * http://weblogs.java.net/blog/alexfromsun/
 */
class JXButtonPanelDemo extends JFrame {

    private ButtonGroup radioGroup = new ButtonGroup();

    public JXButtonPanelDemo() {
        super("JXButtonPanel demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel topPanel = new JPanel(new GridLayout(1, 0));

        final JXButtonPanel radioGroupPanel = createRadioJXButtonPanel();
        topPanel.add(radioGroupPanel);

        final JXButtonPanel checkBoxPanel = createCheckBoxJXButtonPanel();
        topPanel.add(checkBoxPanel);

        add(topPanel);
        add(createButtonJXButtonPanel(), BorderLayout.SOUTH);
        pack();

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem item = new JMenuItem("Unselect radioButtons");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // hack for 1.5
                // in 1.6 ButtonGroup.clearSelection added
                JRadioButton b = new JRadioButton();
                radioGroup.add(b);
                b.setSelected(true);
                radioGroup.remove(b);
            }
        });
        menu.add(item);
        bar.add(menu);
        setJMenuBar(bar);

        setSize(300, 300);
        setLocationRelativeTo(null);
    }

    private JXButtonPanel createRadioJXButtonPanel() {
        JXButtonPanel panel = new JXButtonPanel();
        panel.setLayout(new GridLayout(0, 1));
        JRadioButton one = new JRadioButton("One");
        panel.add(one);
        radioGroup.add(one);
        JRadioButton two = new JRadioButton("Two");
        panel.add(two);
        radioGroup.add(two);
        JRadioButton three = new JRadioButton("Three");
        panel.add(three);
        radioGroup.add(three);
        JRadioButton four = new JRadioButton("Four");
        panel.add(four);
        radioGroup.add(four);
        one.setSelected(true);
        panel.setBorder(BorderFactory.createTitledBorder("JXButtonPanel"));
        return panel;
    }

    private JXButtonPanel createCheckBoxJXButtonPanel() {
        JXButtonPanel panel = new JXButtonPanel();
        panel.setLayout(new GridLayout(0, 1));
        JCheckBox one = new JCheckBox("One");
        panel.add(one);
        JCheckBox two = new JCheckBox("Two");
        panel.add(two);
        JCheckBox three = new JCheckBox("Three");
        panel.add(three);
        JCheckBox four = new JCheckBox("Four");
        panel.add(four);
        panel.setBorder(BorderFactory.createTitledBorder("JXButtonPanel"));
        return panel;
    }

    private JPanel createButtonJXButtonPanel() {
        JPanel ret = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Does JXButtonPanel support arrow keys ?");
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        JPanel temp = new JPanel();
        temp.add(label);
        ret.add(temp);

        JXButtonPanel panel = new JXButtonPanel();
        panel.setCyclic(true);
        panel.add(new JButton("Yes"));
        panel.add(new JButton("Sure"));
        panel.add(new JButton("Absolutely !"));
        panel.setBorder(BorderFactory.createTitledBorder(null,
                "JXButtonPanel.setCyclic(true)",
                TitledBorder.CENTER, TitledBorder.BOTTOM));
        ret.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        ret.add(panel, BorderLayout.SOUTH);
        return ret;
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new JXButtonPanelDemo().setVisible(true);
            }
        });
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


