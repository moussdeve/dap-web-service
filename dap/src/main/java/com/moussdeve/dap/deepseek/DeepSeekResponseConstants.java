//*****************************************************************************************************************************************************************************
// *	Title		:	DeepSeekResponseConstants
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0
// * 	Description	:	Holds DeepSeek response constants used for testing purposes
// *==========================================================================================================================================================================
// *
// *	Dependencies:	NONE
// *	Usage		:	
// *	Notes		:	
//*****************************************************************************************************************************************************************************

package com.moussdeve.dap.deepseek;

public class DeepSeekResponseConstants {
    public static final String Crate61 = "{\r\n" + //
                "    \"id\": \"8fa339ce-bd79-4383-aeff-479cf098edea\",\r\n" + //
                "    \"object\": \"chat.completion\",\r\n" + //
                "    \"created\": 1757044289,\r\n" + //
                "    \"model\": \"deepseek-chat\",\r\n" + //
                "    \"choices\": [\r\n" + //
                "        {\r\n" + //
                "            \"index\": 0,\r\n" + //
                "            \"message\": {\r\n" + //
                "                \"role\": \"assistant\",\r\n" + //
                "                \"content\": \"Of course. Here is a list of promo and coupon codes for Crate 61, presented in a table format.\\n" + //
                "\\n" + //
                "**Important Note:** Promo codes can change frequently. If a code does not work at checkout, I recommend checking their official website's banner, signing up for their newsletter, or visiting a reputable coupon code aggregator for the most current offers.\\n" + //
                "\\n" + //
                "### Crate 61 Promo Codes\\n" + //
                "\\n" + //
                "| Promo Code | Discount / Offer                                  | Typical Terms & Conditions                                                                 | Status       |\\n" + //
                "| :--------- | :------------------------------------------------ | :----------------------------------------------------------------------------------------- | :----------- |\\n" + //
                "| **WELCOME15** | 15% off your first order                         | For new email subscribers only. Valid on full-price items.                                 | Usually Active |\\n" + //
                "| **SHIPFREE** | Free Standard Shipping on orders over $75         | Valid on orders $75+ before tax and shipping. May exclude oversized items.                 | Usually Active |\\n" + //
                "| **SUMMER25** | 25% off your entire purchase                     | Seasonal sale. Often valid for a limited time. May exclude sale items or certain brands.   | Seasonal     |\\n" + //
                "| **BUNDLE10** | 10% off when you buy 2 or more furniture items    | Applies to qualifying furniture. Cannot be combined with other offers.                     | Usually Active |\\n" + //
                "| **CLEARANCE**| Extra 20% off all sale items                      | Automatically applied at checkout to items already on sale.                                | Varies       |\\n" + //
                "| **REFER20**  | 20% off for you and a friend                      | Through their referral program. You receive a code after a friend makes their first purchase. | Active Program |\\n" + //
                "\\n" + //
                "### How to Find a Working Code if These Don't Work:\\n" + //
                "\\n" + //
                "1.  **Newsletter Sign-Up:** The most reliable way to get a code is to enter your email on their website footer. They often send a **WELCOME15** or **WELCOME10** code immediately.\\n" + //
                "2.  **Check Website Banner:** Always look at the top banner or top of the homepage for their current site-wide promotion.\\n" + //
                "3.  **Sale Seasons:** Check around major holidays (4th of July, Labor Day, Black Friday, etc.) for special limited-time codes.\\n" + //
                "\\n" + //
                "I hope you find a great deal on your purchase\"\r\n" + //
                "            },\r\n" + //
                "            \"logprobs\": null,\r\n" + //
                "            \"finish_reason\": \"stop\"\r\n" + //
                "        }\r\n" + //
                "    ],\r\n" + //
                "    \"usage\": {\r\n" + //
                "        \"prompt_tokens\": 31,\r\n" + //
                "        \"completion_tokens\": 471,\r\n" + //
                "        \"total_tokens\": 502,\r\n" + //
                "        \"prompt_tokens_details\": {\r\n" + //
                "            \"cached_tokens\": 0\r\n" + //
                "        },\r\n" + //
                "        \"prompt_cache_hit_tokens\": 0,\r\n" + //
                "        \"prompt_cache_miss_tokens\": 31\r\n" + //
                "    },\r\n" + //
                "    \"system_fingerprint\": \"fp_08f168e49b_prod0820_fp8_kvcache\"\r\n" + //
                "}";

    public static final String GroveCo = "{\r\n" + //
                "    \"id\": \"88e75c57-2041-4866-8823-84cefb27c1e8\",\r\n" + //
                "    \"object\": \"chat.completion\",\r\n" + //
                "    \"created\": 1757044189,\r\n" + //
                "    \"model\": \"deepseek-chat\",\r\n" + //
                "    \"choices\": [\r\n" + //
                "        {\r\n" + //
                "            \"index\": 0,\r\n" + //
                "            \"message\": {\r\n" + //
                "                \"role\": \"assistant\",\r\n" + //
                "                \"content\": \"Of course. Here is a list of current and commonly available promo codes for Grove Collaborative (Grove Co.), presented in a table format.\\n" + //
                "\\n" + //
                "**Important Note:** Promo codes can change frequently and are often targeted to specific users or regions. I recommend trying a few from this list at checkout to see which one provides the best discount for your particular cart.\\n" + //
                "\\n" + //
                "### Grove Co. Promo Codes & Offers\\n" + //
                "\\n" + //
                "| Promo Code | Discount Offer | Best For / Notes |\\n" + //
                "| :--- | :--- | :--- |\\n" + //
                "| **HELLOGROVE** | **Free VIP Shipping + 5-piece gift** | **New Customers.** This is their most common and valuable starter offer. |\\n" + //
                "| **WELCOME** | Varies (often 20% off + free gift) | New Customers. A reliable alternative to the code above. |\\n" + //
                "| **GROVE20** | 20% off your order | New or existing customers. A general-purpose discount code. |\\n" + //
                "| **SHIPFREE** | Free shipping on orders over $49 | All customers. Standard free shipping threshold. |\\n" + //
                "| **CLEAN15** | 15% off your order | A good fallback if other codes aren't working. |\\n" + //
                "| **REFER** | Friend gives you $10, you get $10 | Referring a friend (not a traditional checkout code). |\\n" + //
                "| (None needed) | **Build a Bundle** for 20-30% off | **All customers.** This is a site-wide promotion on bundled items, often the best deal. |\\n" + //
                "\\n" + //
                "---\\n" + //
                "\\n" + //
                "### How to Get the Best Deal at Grove Co.:\\n" + //
                "\\n" + //
                "1.  **New Customers:** Always use **HELLOGROVE** or **WELCOME** for your first order. This typically includes a free gift set (like a glass spray bottle, soap dispenser, and cleaning concentrates) and free shipping, which is a fantastic value.\\n" + //
                "2.  **Build a Bundle:** Look for the \\\"Build a Bundle\\\" option on the site. This is not a code but a promotion where you can select several full-size products (e.g., hand soap, dish soap, household cleaner) for a significant discount, often better than a standard percentage-off code.\\n" + //
                "3.  **Free Shipping:** If you don't have a code, remember that orders over $49 typically ship for free. You can use code **SHIPFREE** to ensure this is applied.\\n" + //
                "4.  **Check their Sale Page:** Grove Co. has a permanent \\\"Sale\\\" section on their website with already-discounted items. You can often apply a promo code on top of these sale prices.\\n" + //
                "\\n" + //
                "**Pro Tip:** The most reliable way to find a working code is to sign up for their newsletter with your email address. They will usually send you a welcome offer immediately. You can also try opening the site in an incognito/private browser window to be recognized as a new visitor, which often triggers a pop-up with a special offer.\"\r\n" + //
                "            },\r\n" + //
                "            \"logprobs\": null,\r\n" + //
                "            \"finish_reason\": \"stop\"\r\n" + //
                "        }\r\n" + //
                "    ],\r\n" + //
                "    \"usage\": {\r\n" + //
                "        \"prompt_tokens\": 29,\r\n" + //
                "        \"completion_tokens\": 604,\r\n" + //
                "        \"total_tokens\": 633,\r\n" + //
                "        \"prompt_tokens_details\": {\r\n" + //
                "            \"cached_tokens\": 0\r\n" + //
                "        },\r\n" + //
                "        \"prompt_cache_hit_tokens\": 0,\r\n" + //
                "        \"prompt_cache_miss_tokens\": 29\r\n" + //
                "    },\r\n" + //
                "    \"system_fingerprint\": \"fp_08f168e49b_prod0820_fp8_kvcache\"\r\n" + //
                "}";
}
