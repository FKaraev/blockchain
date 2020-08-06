package blockchain;

import java.util.concurrent.Callable;

public class Miner implements Callable<Block> {

    private BlockChain blockChain;

    public Miner(BlockChain blockChain){
        this.blockChain = blockChain;
    }

    @Override
    public Block call() throws Exception {
        return null;
    }
}
