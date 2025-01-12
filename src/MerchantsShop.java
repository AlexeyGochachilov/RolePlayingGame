import java.util.ArrayList;

public class MerchantsShop {

    private ArrayList<Items> itemsMerchantsShop = new ArrayList<>(20);

    public ArrayList<Items> getItemsMerchantsShop() {
        return itemsMerchantsShop;
    }

    //покупка предметов
    public void buyItems(Person player, Items items, World.TrueOreFalse merchantsShopMarket) {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.getBackpack().add(items);
        if (player.getBackpack().size() <= 20 && player.getGold() >= 1) {
            player.setGold(player.getGold() - items.getGold());
            merchantsShopMarket.trueTrue();
        } else if (player.getBackpack().size() > 20) {
            player.getBackpack().remove(20);
            merchantsShopMarket.falseFalse();
        } else if (player.getGold() < 1) merchantsShopMarket.falseFalse();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //показ предметов в магазине
    public void lookAtItems() {

        int counter = 1;
        for (Items it : itemsMerchantsShop) {
            System.out.println(counter + ": " + it.toString());
            counter++;
        }
    }

}

