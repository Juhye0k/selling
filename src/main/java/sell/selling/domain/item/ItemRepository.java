package sell.selling.domain.item;

import lombok.extern.slf4j.Slf4j;
import sell.selling.domain.member.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ItemRepository {

    private static final Map<Long, Item> store=new HashMap<>();
    private static Long sequence=0L;

    public Item save(Item item)
    {
        item.setId(++sequence);
        store.put(item.getId(),item);
        log.info("현재까지 저장된 아이템={}",findAll());
        return item;
    }

    public Item findById(Long id)
    {
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long id,Item updateParam)
    {
        Item findItem=findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void delete(Item item)
    {
        store.remove(item.getId());
    }
}
