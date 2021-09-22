<template>
  <v-container fluid>
    <v-dialog v-model="dialog">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="blue white"
          elevation="2"
          outlined
          x-large
          v-bind="attrs"
          v-on="on"
          >학교 추가</v-btn
        >
      </template>

      <v-card>
        <v-card-title>
          <span class="text-h5">학교 추가 양식</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-text-field
                v-model="school_name"
                label="학교 이름"
                hint="학교 이름"
              ></v-text-field>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="create"> 생성 </v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">
            창 닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">Name</th>
            <th class="text-left">Calories</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in desserts" :key="item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.calories }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import * as school_api from "@/api/school";

export default {
  name: "School",

  data: () => ({
    dialog: false,
    school_name: "",
    desserts: [
      {
        name: "Frozen Yogurt",
        calories: 159,
      },
      {
        name: "Ice cream sandwich",
        calories: 237,
      },
      {
        name: "Eclair",
        calories: 262,
      },
      {
        name: "Cupcake",
        calories: 305,
      },
      {
        name: "Gingerbread",
        calories: 356,
      },
      {
        name: "Jelly bean",
        calories: 375,
      },
      {
        name: "Lollipop",
        calories: 392,
      },
      {
        name: "Honeycomb",
        calories: 408,
      },
      {
        name: "Donut",
        calories: 452,
      },
      {
        name: "KitKat",
        calories: 518,
      },
    ],
  }),

  methods: {
    create() {
      console.log(process.env);
      school_api
        .create_school(this.school_name)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
