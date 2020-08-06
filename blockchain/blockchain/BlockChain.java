package blockchain;

import java.util.*;

public class BlockChain {
    private int id = 1;
    Map<String, Block> blocks;
    private Block lastBlock;
    private final Random random;
    List<Float> timeSpentOnCreatingBlock;

    public BlockChain() {
        blocks = new HashMap<>();
        random = new Random(new Date().getTime());
        timeSpentOnCreatingBlock = new ArrayList<>();
    }

    public void createBlock(int numberOfZeros) {
        //creating block fields
        long start = new Date().getTime();
        String hashOfLastBlock = lastBlock != null ? lastBlock.getHashOfBlock() : "0";

        Pair hashMagicNumberPair = createHashForBlock(numberOfZeros, hashOfLastBlock);

        String hash = hashMagicNumberPair.hash;
        int magicNumber = hashMagicNumberPair.magicNumber;
        long timestamp = new Date().getTime();

        //creating new block and connecting it to chain
        Block newBlock = new Block(id, hash, hashOfLastBlock, timestamp, magicNumber);
        blocks.put(String.valueOf(id), newBlock);
        long finish = new Date().getTime();

        lastBlock = newBlock;
        id = id + 1;
        float timeSpent = (finish - start) / 1000f;
        timeSpentOnCreatingBlock.add(timeSpent);
    }

    public boolean validateChain() {
        Block prevBlock = null;
        boolean valid = true;
        for (Map.Entry<String, Block> entry : blocks.entrySet()) {
            if (prevBlock == null) {
                valid = valid && isBlockValid(entry.getKey(), "0");
            } else {
                valid = valid && isBlockValid(entry.getKey(), entry.getValue().getHashOfBlock());
            }
            prevBlock = entry.getValue();
        }
        return valid;
    }

    private Pair createHashForBlock(int numberOfZeros,
                                    String hashOfLastBlock) {
        String zeros = "0".repeat(numberOfZeros);
        String hash;
        int number = 1;
        do {
            hash = Hasher.getHash(String.valueOf(id + number));
            number = random.nextInt(Integer.MAX_VALUE);
        } while (hash.equals(hashOfLastBlock) ||
                !hash.startsWith(zeros)
        );

        return new Pair(number, hash);
    }

    private boolean isBlockValid(String curHash, String prevHash) {
        return blocks.get(curHash).getHashOfPreviousBlock().equals(prevHash);
    }

    public void printChain() {
        blocks.forEach((s, block) ->
                System.out.println(block +
                        "Block was generating for: " +
                        timeSpentOnCreatingBlock.get(block.getId() - 1) + "seconds\n")
        );
    }

    public void printBlock(int id) {
        System.out.println(blocks.get(String.valueOf(id)));
        System.out.println("Time spent: " + timeSpentOnCreatingBlock.get(id - 1) + "\n");
    }


    static class Pair {
        int magicNumber;
        String hash;

        Pair(int first, String second) {
            magicNumber = first;
            hash = second;
        }
    }
}
