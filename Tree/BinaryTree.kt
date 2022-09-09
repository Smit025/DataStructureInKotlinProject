package Tree

import java.util.*
import kotlin.math.max

class BinaryTree {

    var root: TreeNode? = null

    fun addNode(node: TreeNode?, data: Int): TreeNode? {
        if (node != null) {
            if (node.data >= data) {
                // When new element is smaller or
                // equal to current node
                node.left = this.addNode(node.left, data)
            } else {
                // When new element is higher to current node
                node.right = this.addNode(node.right, data)
            }
            // important to manage root node
            return node
        } else {
            return TreeNode(data)
        }
    }

    fun ifContain(node: TreeNode?, value: Int): Boolean {
        if (node == null) {
            return false
        }
        if (node.data == value) {
            return true
        }
        val ifLeft = ifContain(node.left, value)

        if (ifLeft) {
            return true
        }

        val ifRight = ifContain(node.right, value)
        if (ifRight) {
            return true
        }
        return ifRight
    }

    // Display preorder
    fun preorder(node: TreeNode?): Unit {
        if (node != null) {
            // Display node value
            print("  " + node.data)
            // Visit to left subtree
            this.preorder(node.left)
            // Visit to right subtree
            this.preorder(node.right)
        }
    }

    fun inorder(node: TreeNode?): Unit {
        if (node != null) {
            // Visit to left subtree
            this.inorder(node.left)
            // Display node value
            print("  " + node.data)
            // Visit to right subtree
            this.inorder(node.right)
        }
    }

    fun postorder(node: TreeNode?): Unit {
        if (node != null) {
            // Visit to left subtree
            this.postorder(node.left)
            // Visit to right subtree
            this.postorder(node.right)
            // Display node value
            print("  " + node.data)
        }
    }

    fun deleteNode(node: TreeNode?, data: Int): TreeNode? {
        if (node == null) {
            return node
        } else if (data < node.data) {
            node.left = deleteNode(node.left, data)
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data)
        } else {
            //Case 1 : No  Child
            if (node.left == null && node.right == null) {
                //      node = TreeNode(null)
                return null
            }
            //Case 2 : One child
            else if (node.left == null) {
                var temp = node
                //       node = node.right
                //       delete temp
                return node
            } else if (node.right == null) {
                var temp = node
                //       node = node.left
                //       delete temp
                return node
            }
            //Case 3 : Two child
            else {
                val temp = findMin(node.right)
                node.data = temp.data
                node.right = deleteNode(node.right, node.data)
            }
        }
        return node
    }

    private fun findMin(node: TreeNode?): TreeNode {
        if (node != null) {
            node.right = findMin(node.right)
        }
        return node!!
    }

    fun findHeight(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }
        return max(findHeight(node.left), findHeight(node.right)).plus(1)
    }

    fun findMax(node: TreeNode?): Int {
        if (node == null) {
            return Integer.MIN_VALUE
        }
        return Math.max(node.data, Math.max(findMax(node.left), findMax(node.right)))
    }

    fun levelOrderTraversal(node: TreeNode) {

        var q = LinkedList<TreeNode>()
        q.add(node)
        while(q.isNotEmpty()){
            val current  = q.poll()
            println(current.data)
            if(current.left!=null){
                q.add(current.left!!)
            }
            if(current.right!=null){
                q.add(current.right!!)
            }
        }
    }
}

fun main() {
    val binaryTree = BinaryTree().apply {
        root = addNode(root, 10);
        addNode(root, 4)
        addNode(root, 3)
        addNode(root, 5)
        addNode(root, 15)
        addNode(root, 12)

        // Display tree nodes
        println("Preorder ")
        preorder(root)
        println("\nInorder ")
        inorder(root)
        println("\nPostorder ")
        postorder(root)
        println("\n")
        println("Node Present : ${ifContain(root, 51)}")
        deleteNode(root, 15)
        println("Height is  : ${findHeight(root)}")
        println("Max element in Binary tree is : ${findMax(root)}")
        root?.let { levelOrderTraversal(it) }
    }
}
