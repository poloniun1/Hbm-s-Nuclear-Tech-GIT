package com.hbm.inventory.recipes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.hbm.inventory.OreDictManager.*;
import static com.hbm.inventory.material.Mats.*;
import static com.hbm.inventory.material.MaterialShapes.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.inventory.FluidStack;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.inventory.material.Mats;
import com.hbm.inventory.material.Mats.MaterialStack;
import com.hbm.inventory.recipes.loader.SerializableRecipe;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.items.machine.ItemScraps;

import net.minecraft.item.ItemStack;

public class RotaryFurnaceRecipes extends SerializableRecipe {
	
	public static List<RotaryFurnaceRecipe> recipes = new ArrayList();

	@Override
	public void registerDefaults() {
		
		recipes.add(new RotaryFurnaceRecipe(new MaterialStack(MAT_STEEL, INGOT.q(1)), 200, 100,
				new OreDictStack(IRON.ingot()), new OreDictStack(ANY_COKE.gem())
			));
	}
	
	public static HashMap getRecipes() {

		HashMap<Object, Object> recipes = new HashMap<Object, Object>();
		
		for(RotaryFurnaceRecipe recipe : RotaryFurnaceRecipes.recipes) {
			
			int size = recipe.ingredients.length + (recipe.fluid != null ? 1 : 0);
			Object[] array = new Object[size];
			
			for(int i = 0; i < recipe.ingredients.length; i++) {
				array[i] = recipe.ingredients[i];
			}
			
			if(recipe.fluid != null) array[size - 1] = ItemFluidIcon.make(recipe.fluid);
			
			recipes.put(array, ItemScraps.create(recipe.output, true));
		}
		
		return recipes;
	}
	
	public static RotaryFurnaceRecipe getRecipe(ItemStack... inputs) {
		
		outer:
		for(RotaryFurnaceRecipe recipe : recipes) {

			List<AStack> recipeList = new ArrayList();
			for(AStack ingredient : recipe.ingredients) recipeList.add(ingredient);
			
			for(int i = 0; i < inputs.length; i++) {
				
				ItemStack inputStack = inputs[i];

				if(inputStack != null) {
					
					boolean hasMatch = false;
					Iterator<AStack> iterator = recipeList.iterator();

					while(iterator.hasNext()) {
						AStack recipeStack = iterator.next();

						if(recipeStack.matchesRecipe(inputStack, true) && inputStack.stackSize >= recipeStack.stacksize) {
							hasMatch = true;
							recipeList.remove(recipeStack);
							break;
						}
					}

					if(!hasMatch) {
						continue outer;
					}
				}
			}
			
			if(recipeList.isEmpty()) return recipe;
		}
		
		return null;
	}

	@Override
	public String getFileName() {
		return "hbmRotaryFurnace.json";
	}

	@Override
	public Object getRecipeObject() {
		return recipes;
	}

	@Override
	public void deleteRecipes() {
		recipes.clear();
	}

	@Override
	public void readRecipe(JsonElement recipe) {
		JsonObject obj = (JsonObject) recipe;
		
		AStack[] inputs = this.readAStackArray(obj.get("inputs").getAsJsonArray());
		FluidStack fluid = obj.has("fluid") ? this.readFluidStack(obj.get("fluid").getAsJsonArray()) : null;
		
		JsonArray array = obj.get("output").getAsJsonArray();
		MaterialStack stack = new MaterialStack(Mats.matByName.get(array.get(0).getAsString()), array.get(1).getAsInt());
		
		int duration = obj.get("duration").getAsInt();
		int steam = obj.get("steam").getAsInt();
		
		recipes.add(new RotaryFurnaceRecipe(stack, duration, steam, fluid, inputs));
	}

	@Override
	public void writeRecipe(Object obj, JsonWriter writer) throws IOException {
		RotaryFurnaceRecipe recipe = (RotaryFurnaceRecipe) obj;
		
		writer.name("inputs").beginArray();
		for(AStack aStack : recipe.ingredients) {
			this.writeAStack(aStack, writer);
		}
		writer.endArray();
		
		if(recipe.fluid != null) {
			writer.name("fluid");
			this.writeFluidStack(recipe.fluid, writer);
		}
		
		writer.name("output").beginArray();
		writer.setIndent("");
		writer.value(recipe.output.material.names[0]).value(recipe.output.amount);
		writer.endArray();
		writer.setIndent("  ");

		writer.name("duration").value(recipe.duration);
		writer.name("steam").value(recipe.steam);
	}
	
	public static class RotaryFurnaceRecipe {
		
		public AStack[] ingredients;
		public FluidStack fluid;
		public MaterialStack output;
		public int duration;
		public int steam;
		
		public RotaryFurnaceRecipe(MaterialStack output, int duration, int steam, FluidStack fluid, AStack... ingredients) {
			this.ingredients = ingredients;
			this.fluid = fluid;
			this.output = output;
			this.duration = duration;
			this.steam = steam;
		}
		
		public RotaryFurnaceRecipe(MaterialStack output, int duration, int steam, AStack... ingredients) {
			this(output, duration, steam, null, ingredients);
		}
	}
}