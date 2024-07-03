package tool_rental.models;

import java.util.HashMap;
import java.util.Map;

import tool_rental.constants.AppConstants;

/**
 * A simple in-memory database to store the relavant tools. For a more robust and production ready
 * solution, we would of course want to use a real database system.
 */
public class ToolInventory {
    private static final Map<String, Tool> toolInventory = new HashMap<>();

    static {
        toolInventory.put(AppConstants.CHAINSAW_CODE, new Tool(AppConstants.CHAINSAW_CODE, Tool.ToolType.CHAINSAW, AppConstants.STIHL_BRAND));
        toolInventory.put(AppConstants.LADDER_CODE, new Tool(AppConstants.LADDER_CODE, Tool.ToolType.LADDER, AppConstants.WERNER_BRAND));
        toolInventory.put(AppConstants.JACKHAMMER_DEWALT_CODE, new Tool(AppConstants.JACKHAMMER_DEWALT_CODE, Tool.ToolType.JACKHAMMER, AppConstants.DEWALT_BRAND));
        toolInventory.put(AppConstants.JACKHAMMER_RIDGID_CODE, new Tool(AppConstants.JACKHAMMER_RIDGID_CODE, Tool.ToolType.JACKHAMMER, AppConstants.RIDGID_BRAND));
    }

    public static Tool getToolByCode(String code) {
        Tool tool = toolInventory.get(code);
        if (tool == null) {
            throw new IllegalArgumentException(AppConstants.TOOL_NOT_FOUND + code);
        }
        return tool;
    }

    public static void addTool(Tool tool) {
        toolInventory.put(tool.getCode(), tool);
    }

    public static void removeTool(String code) {
        if (!toolInventory.containsKey(code)) {
            throw new IllegalArgumentException(AppConstants.TOOL_NOT_FOUND + code);
        }
        toolInventory.remove(code);
    }

    public static Map<String, Tool> getAllTools() {
        return new HashMap<>(toolInventory);
    }

    public static void clearInventory() {
        toolInventory.clear();
    }
}