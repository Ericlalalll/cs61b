import org.w3c.dom.Node;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * @BelongsProject: lab9 _2019
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Eric
 * @CreateTime: 2022-07-25  23:35
 * @Description: TODO
 */
public class MyTrieSet implements TrieSet61B {
    private static class Node {
        boolean isKey;
        Map<Character, Node> children; // better refer to the pict on notes. key is char, value is the next node

        Node(boolean isKey) {
            this.isKey = isKey;
            children = new HashMap<>();
        }
    }

    private Node root;
    public MyTrieSet() {
        root = new Node(false);
    }

    @Override
    public void clear() {
        root = new Node(false);
    }

    /** Iterate the given string(key)
     * return the last character of this given string
     * return null if its not in the Trie
     */
    private Node stringIterator(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.children.containsKey(c)) {
                return null;
            }
            curr = curr.children.get(c);
        }
        return curr;
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("illegal input");
        }
        Node endNode = stringIterator(key);
        if (endNode == null) {
            return false;
        }
        return endNode.isKey;
    }

    public void add(String key) {
        if (key == null || key.length() == 0) {
            return;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node(false));
            }
            curr = curr.children.get(c);
        }
        curr.isKey = true;
    }

    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("illegal input");
        }
        Node startNode = stringIterator(prefix);
        if (startNode == null) {
            return null;
        }
        List<String> keys = new ArrayList<>();
        collectHelper(prefix, startNode, keys);
        return keys;
    }

    private void collectHelper(String s, Node node, List<String> keys) {
        if (node.isKey) {
            keys.add(s);
        }
        for (char c : node.children.keySet()) {
            collectHelper(s + c, node.children.get(c), keys);
        }
    }

    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

}
