import http from "./http";

export async function create_school(school_name) {
  return http.post(
    "/api/v1/classroom/",
    {
      school_name,
    },
    {
      "content-type": "application/json",
      Accept: "application/json",
    }
  );
}
