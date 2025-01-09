import java.util.ArrayList;

public class MerchantsShop {

    private ArrayList<Items> itemsMerchantsShop = new ArrayList<>(20);

    public ArrayList<Items> getItemsMerchantsShop() {
        return itemsMerchantsShop;
    }

    public void sellItems(Person player, Items items, World.MerchantsShopMarketSell merchantsShopMarket) {

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (player.getBackpack().contains(items)) {
                player.getBackpack().remove(items);
                player.setGold(player.getGold() + items.getGold() / 2);
                merchantsShopMarket.MerchantsShopSellTrue();
            } else {
                merchantsShopMarket.MerchantsShopSellFalse();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void buyItems(Person player, Items items, World.MerchantsShopMarketBuy merchantsShopMarket) {

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.getBackpack().add(items);
            if (player.getBackpack().size() < 20) {
                player.setGold(player.getGold() - items.getGold());
                merchantsShopMarket.MerchantsShopBuyTrue();
            } else if (player.getBackpack().size() == 20) {
                player.getBackpack().remove(20);
                merchantsShopMarket.MerchantsShopBuyFalse();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void lookAtItems() {

        int counter = 1;
        for (Items it : itemsMerchantsShop) {
            System.out.println(counter + ": " + it.toString());
            counter++;
        }
    }

}

