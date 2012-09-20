/** 
 * Copyright (c) Krapht, 2011
 * 
 * "LogisticsPipes" is distributed under the terms of the Minecraft Mod Public 
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package logisticspipes.utils;

import java.util.LinkedList;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public final class ItemIdentifierStack {
	private final ItemIdentifier _item;
	public int stackSize;
	
	public static ItemIdentifierStack GetFromStack(ItemStack stack){
		return new ItemIdentifierStack(ItemIdentifier.get(stack), stack.stackSize);
	}
	
	public ItemIdentifierStack(ItemIdentifier item, int stackSize){
		_item = item;
		this.stackSize = stackSize;
	}
	
	public ItemIdentifier getItem(){
		return _item;
	}

	public ItemStack makeNormalStack(){
		ItemStack stack = new ItemStack(_item.itemID, this.stackSize, _item.itemDamage);
		stack.setTagCompound(_item.tag);
		return stack;
	}
	
	public boolean equals(Object object) {
		if(object instanceof ItemIdentifierStack) {
			ItemIdentifierStack stack = (ItemIdentifierStack)object;
			return stack._item.equals(this._item) && stack.stackSize == this.stackSize;
		}
		return false;
	}
	
	public String toString() {
		return new StringBuilder(Integer.toString(stackSize)).append("x").append(_item.toString()).toString();
	}
	
	public ItemIdentifierStack clone() {
		return new ItemIdentifierStack(_item, stackSize);
	}

	public static LinkedList<ItemIdentifierStack> getListFromInventory(IInventory inv) {
		LinkedList<ItemIdentifierStack> list = new LinkedList<ItemIdentifierStack>();
		for(int i=0;i<inv.getSizeInventory();i++) {
			if(inv.getStackInSlot(i) == null) {
				list.add(null);
			} else {
				list.add(ItemIdentifierStack.GetFromStack(inv.getStackInSlot(i)));
			}
		}
		return list;
	}
}