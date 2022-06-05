package com.tq.huffman;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
    public static void main(String[] args) {
        String src = "i like like like java do you like a java";

        // 编码
        Pair<byte[], Map<Byte, String>> encodePair = encode(src);

        byte[] encode = encodePair.getLeft();
        Map<Byte, String> huffmanCode = encodePair.getRight();

        // 打印
        System.out.println(String.format("编码后的byte数组：%s", Arrays.toString(encode)));
        System.out.println("huffman code：");
        huffmanCode.forEach((k, v) -> System.out.printf("%s->%s ", k, v));
        System.out.println();

        // 解码
        String result = decode(encode, huffmanCode);
        System.out.println("解码结果：" + result);
    }

    public static String decode(byte[] encode, Map<Byte, String> huffmanCode) {
        // 将byte数组还原为01码
        String s = byteToString(encode);
        // 反转huffman code
        Map<String, Byte> huffmanCodeRevert = new HashMap<>(huffmanCode.size());
        huffmanCode.forEach((k, v) -> huffmanCodeRevert.put(v, k));
        // 匹配
        return doDecode(s, huffmanCodeRevert);
    }

    private static String byteToString(byte[] encode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < encode.length; i++) {
            int tmp = encode[i];
            String result;
            if (i == encode.length - 1) {
                // 最后一个不需要补位
                result = Integer.toBinaryString(tmp);
            } else {
                // 如果是正数我们还存在补高位
                tmp |= 256;
                String s = Integer.toBinaryString(tmp);
                result = s.substring(s.length() - 8);
            }
            stringBuilder.append(result);
        }
        return stringBuilder.toString();
    }

    private static String doDecode(String s, Map<String, Byte> huffmanCode) {
        List<Byte> resultByte = new ArrayList<>();
        int len = 1;
        for (int i = 0; i < s.length(); ) {
            String substring = s.substring(i, i + len);
            Byte b = huffmanCode.get(substring);
            if (b == null) {
                len++;
                continue;
            }
            // 匹配到结果
            resultByte.add(b);
            i += len;
            len = 1;
        }
        byte[] bytes = new byte[resultByte.size()];
        for (int i = 0; i < resultByte.size(); i++) {
            bytes[i] = resultByte.get(i);
        }
        return new String(bytes);
    }

    public static Pair<byte[], Map<Byte, String>> encode(String src) {
        byte[] srcByte = src.getBytes();
        List<Node> nodes = getNodes(srcByte);

        Node root = creatHuffmanTree(nodes);

        Map<Byte, String> huffmanCode = getCodes(root);

        return Pair.of(doEncode(srcByte, huffmanCode), huffmanCode);
    }

    private static byte[] doEncode(byte[] srcByte, Map<Byte, String> huffmanCode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : srcByte) {
            stringBuilder.append(huffmanCode.get(b));
        }
        System.out.println(String.format("编码后的01码：%s", stringBuilder));

        String s = stringBuilder.toString();
        int length;
        if (s.length() % 8 == 0) {
            length = s.length() / 8;
        } else {
            length = s.length() / 8 + 1;
        }
        byte[] result = new byte[length];
        int index = 0;
        for (int i = 0; i < s.length(); i += 8) {
            byte b;
            if (index == length - 1) {
                b = (byte) Integer.parseInt(s.substring(i), 2);
            } else {
                b = (byte) Integer.parseInt(s.substring(i, i + 8), 2);
            }
            result[index++] = b;
        }
        return result;
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        return getCodes(root, null, new StringBuilder());
    }

    private static Map<Byte, String> getCodes(Node node, String code, StringBuilder stringBuilder) {
        Map<Byte, String> map = new HashMap<>();
        StringBuilder stringBuilderNew = new StringBuilder(stringBuilder);
        if (code != null) {
            stringBuilderNew.append(code);
        }
        if (node == null) {
            return new HashMap<>();
        }
        if (node.getData() == null) {
            map.putAll(getCodes(node.getLeft(), "0", stringBuilderNew));
            map.putAll(getCodes(node.getRight(), "1", stringBuilderNew));
        } else {
            map.put(node.getData(), stringBuilderNew.toString());
        }
        return map;
    }

    private static Node creatHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodes.add(parentNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        return nodes.get(0);
    }

    private static List<Node> getNodes(byte[] srcByte) {
        // <字符, 字符出现的次数>
        Map<Byte, Integer> sum = new HashMap<>();
        for (byte b : srcByte) {
            Integer count = sum.get(b);
            if (count == null) {
                sum.put(b, 1);
            } else {
                sum.put(b, count + 1);
            }
        }

        List<Node> nodes = new ArrayList<>();
        sum.forEach((key, value) -> {
            Node node = new Node(key, value);
            nodes.add(node);
        });
        return nodes;
    }
}

@Data
class HuffmanTree {
    private Node root;
}

@Data
class Node implements Comparable<Node> {
    private Byte data;
    private int weight;
    private Node left;
    private Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

@Data
class Pair<L, R> {
    private L left;
    private R right;

    public static <L, R> Pair<L, R> of(L l, R r) {
        Pair<L, R> pair = new Pair<>();
        pair.left = l;
        pair.right = r;
        return pair;
    }
}