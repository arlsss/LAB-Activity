import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeMenuApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the root node of the tree
        TreeNode root = new TreeNode("Main Menu");

        // Create child nodes for the root node
        TreeNode option1 = new TreeNode("Option 1");
        TreeNode option2 = new TreeNode("Option 2");
        root.addChild(option1);
        root.addChild(option2);

        // Add more child nodes to Option 1
        TreeNode option1a = new TreeNode("Option 1a");
        TreeNode option1b = new TreeNode("Option 1b");
        option1.addChild(option1a);
        option1.addChild(option1b);

        // Add more child nodes to Option 2
        TreeNode option2a = new TreeNode("Option 2a");
        TreeNode option2b = new TreeNode("Option 2b");
        option2.addChild(option2a);
        option2.addChild(option2b);

        // Display the menu and handle user input
        while (true) {
            displayMenu(root, 0);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Process user input
            if (choice == 0) {
                System.out.println("Exiting the application.");
                break;
            } else {
                TreeNode selectedNode = findNode(root, choice);
                if (selectedNode != null) {
                    if (selectedNode.isLeaf()) {
                        System.out.println("You selected: " + selectedNode.getName());
                        // Add your specific action for the leaf node here
                    } else {
                        // Navigate to the selected node's sub-menu
                        displayMenu(selectedNode, 0);
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    // Method to display the menu
    private static void displayMenu(TreeNode node, int level) {
        // Indent the menu items based on their level in the tree
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        // Display the choice number
        System.out.println(node.getChoice() + ". " + node.getName());

        List<TreeNode> children = node.getChildren();
        if (children.size() > 0) {
            for (int i = 0; i < children.size(); i++) {
                displayMenu(children.get(i), level + 1);
            }
        }
    }

    // Method to find a node in the tree based on its choice number
    private static TreeNode findNode(TreeNode node, int choice) {
        if (node.getChoice() == choice) {
            return node;
        } else {
            List<TreeNode> children = node.getChildren();
            for (TreeNode child : children) {
                TreeNode foundNode = findNode(child, choice);
                if (foundNode != null) {
                    return foundNode;
                }
            }
        }
        return null;
    }
}

// Class to represent a node in the tree
class TreeNode {
    private String name;
    private List<TreeNode> children;
    private int choice;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        // Assign choice based on its position in the parent's children list
        this.choice = children.size() + 1; 
    }

    public String getName() {
        return name;
    }

    public void addChild(TreeNode child) {
        children.add(child);
        // Update choice numbers after adding a child
        for (int i = 0; i < children.size(); i++) {
            children.get(i).choice = i + 1;
        }
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public int getChoice() {
        return choice;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}
