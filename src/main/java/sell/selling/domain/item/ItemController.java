package sell.selling.domain.item;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sell.selling.domain.member.Member;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    ItemRepository itemRepository=new ItemRepository();
    @GetMapping("/items")
    public String items(Model model)
    {
        List<Item> items=itemRepository.findAll();
        model.addAttribute("items",items);
        log.info("등록된 아이템={}",items);
        return "items";
    }
    @GetMapping("/item/add")
    public String showAddItem(Model model)
    {
        model.addAttribute("item",new Item());
        return "item";
    }
    @PostMapping("/item/add")
    public String addItem(@ModelAttribute("Item") Item item,HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("loginMember");
        item.setWhoAddItem(member.getLoginId());
        itemRepository.save(item);
        return "redirect:/items";
    }

    @GetMapping("/item/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId,Model model)
    {
        Item item=itemRepository.findById(itemId);
        model.addAttribute(item);
        return "editForm";
    }
    @PostMapping("/item/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId,@ModelAttribute("item") Item item)
    {
        itemRepository.update(itemId,item);
        return "redirect:/items";

    }
    @GetMapping("/items/{itemId}/delete")
    public String delete(@PathVariable("itemId") Long itemId)
    {
        Item item=itemRepository.findById(itemId);
        itemRepository.delete(item);
        return "redirect:/items";
    }

}
