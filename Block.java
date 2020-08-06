package blockchain;


public class Block {
    private final int id;
    private final long timestamp;
    private final String hash;
    private final String hashOfPreviousBlock;
    private final int magicNumber;

    public Block(int id, String hashOfCurrentBlock, String hashOfPreviousBlock, long timestamp, int magicNumber) {
        this.id = id;
        this.hash = hashOfCurrentBlock;
        this.hashOfPreviousBlock = hashOfPreviousBlock;
        this.timestamp = timestamp;
        this.magicNumber = magicNumber;
    }

    @Override
    public String toString() {
        return String.format("Block:\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block:\n%s\n" +
                        "Hash of the block:\n%s\n",
                id,
                timestamp,
                magicNumber,
                hashOfPreviousBlock,
                hash
        );
    }


    public String getHashOfBlock() {
        return hash;
    }

    public String getHashOfPreviousBlock() {
        return hashOfPreviousBlock;
    }

    public int getId() {
        return id;
    }
}
