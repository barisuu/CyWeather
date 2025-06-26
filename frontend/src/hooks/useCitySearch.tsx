import { useQuery } from "@tanstack/react-query";
import agent from "../agent";

export const useCitySearch = (query: string) => {
    return useQuery({
        queryKey: ["citySearch", query],
        queryFn: async () => {
            const response = await agent.get<City[]>("/cities/search", {
                params: { name: query },
            });
            return response.data;
        },
        enabled: query.length > 2, // Only fetch if query is long enough
    });
};
