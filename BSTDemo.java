// Name: Mallory Phaphol
// Class: CS 3305/Section03
// Term: Spring 2025
// Instructor: Umama Tasnim
import java.util.Scanner;

// Class to represent each node in the Binary Search Tree
class TreeNode {
    int key;
    TreeNode left, right;
    // Constructor
    TreeNode(int key) {
        this.key = key;
        left = null;
        right = null;
    }
}
// Class representing the Binary Search Tree
class BinarySearchTree {
    TreeNode root;
    // Method to insert a key into the BST
    void insert(int key) {
        TreeNode newNode = new TreeNode(key);
        if (root == null) { // If the tree is empty
            root = newNode;
            return;
        }
        TreeNode current = root;
        TreeNode parent = null;
        // Traverse the tree to find the correct spot for insertion
        while (current != null) {
            parent = current;
            if (key == current.key) {
                return; // Duplicate keys are not allowed
            } else if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        // Insert the new node in the correct position
        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    // Method to search for a key in the BST
    boolean search(int key) {
        TreeNode current = root;
        while (current != null) {
            if (key == current.key) {
                return true; // Key found
            } else if (key < current.key) {
                current = current.left; // Move to left subtree
            } else {
                current = current.right; // Move to right subtree
            }
        }
        return false; // Key not found
    }
    // Method to delete a key from the BST
    void delete(int key) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeft = false;
        // Find the node to delete
        while (current != null && current.key != key) {
            parent = current;
            if (key < current.key) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
        }
        if (current == null) {
            return; // Key not found
        }
        // Case 1: Node has no children (leaf node)
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // Case 2: Node has one child (left only)
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        // Case 3: Node has one child (right only)
        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        // Case 4: Node has two children
        else {
            // Find the in-order successor (smallest value in right subtree)
            TreeNode successorParent = current;
            TreeNode successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            // Replace current's key with successor's key
            current.key = successor.key;
            // Remove successor
            if (successorParent == current) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
        }
    }

    // Public method to start inorder traversal
    void inorderTraversal() {
        inorderTraversal(root);
    }

    // Recursive inorder traversal (Left, Root, Right)
    private void inorderTraversal(TreeNode current) {
        if (current == null) {
            return;
        }
        inorderTraversal(current.left);
        System.out.print(current.key + " ");
        inorderTraversal(current.right);
    }

    // Public method to calculate the height of the BST
    int height() {
        return height(root);
    }

    // Recursive method to compute height of the BST
    private int height(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        int leftHeight = height(tree.left);
        int rightHeight = height(tree.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
public class BSTDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scan = new Scanner(System.in);
        int option = 0;

        // Menu loop
        while (option != 6) {
            System.out.println("\nBST Choices: \n1. Insert \n2. Inorder Traversal\n3. Search for a value " +
                    "\n4. Delete a value \n5. Get height of BST \n6. Exit \nSelect option: ");
            option = scan.nextInt();
            scan.nextLine();

            if (option == 1) {
                // Insert values
                System.out.println("Enter values (space-separated): ");
                String userInput = scan.nextLine();
                String[] inputs = userInput.split(" ");
                for (String s : inputs) {
                    int val = Integer.parseInt(s);
                    bst.insert(val);
                    System.out.print("Inserted: " + val + "\n");
                }
            } else if (option == 2) {
                // Display inorder traversal
                System.out.println("\nInorder Traversal:");
                bst.inorderTraversal();
                System.out.println();
            } else if (option == 3) {
                // Search for a value
                System.out.println("Enter value to search: ");
                int searchVal = scan.nextInt();
                boolean found = bst.search(searchVal);
                System.out.println("Search " + searchVal + ": " + (found ? "Found" : "Not Found"));
            } else if (option == 4) {
                // Delete a value
                System.out.println("Enter value to delete: ");
                int delVal = scan.nextInt();
                boolean exists = bst.search(delVal);
                if (exists) {
                    bst.delete(delVal);
                    System.out.println("Deleted: " + delVal);
                } else {
                    System.out.println("Value does not exist in BST.");
                }
            } else if (option == 5) {
                // Get tree height
                System.out.println("Height of BST: " + bst.height());
            }
        }

        System.out.println("Exiting program.");
    }
}
