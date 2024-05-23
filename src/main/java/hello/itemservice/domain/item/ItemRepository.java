package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll () {
        return new ArrayList<Item>(store.values());
    }

    public void update(Long itemId, Item item) {
        Item findItem = findById(itemId);
        findItem.setItemName(item.getItemName());
        findItem.setPrice(item.getPrice());
        findItem.setQuantity(item.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
