package com.greymerk.tweaks.trades;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public class CustomWandererTrades {
	public static void registerCustomTrades() {
		
		Identifier pool = Identifier.ofVanilla("sell_special_items");
		
		TradeOfferHelper.registerWanderingTraderOffers( 
			factories -> {
				factories.addOffersToPool(pool, (entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 32),
					new ItemStack(Items.ELYTRA, 1),
					1, 1, 0.05f
				));
				factories.addOffersToPool(pool, (entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 16),
					new ItemStack(Items.SHULKER_SHELL, 1),
					6, 1, 0.05f
				));
			}
		);
	}
}
