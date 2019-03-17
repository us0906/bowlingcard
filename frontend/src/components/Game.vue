<template>
<div class="game">
    <h1>{{ msg }}</h1>
    <h4>your score: </h4>
    <button @click="bowl(0)">0</button>
    <button @click="bowl(1)">1</button>
    <button @click="bowl(2)">2</button>
    <button @click="bowl(3)">3</button>
    <button @click="bowl(4)">4</button>
    <button @click="bowl(5)">5</button>
    <button @click="bowl(6)">6</button>
    <button @click="bowl(7)">7</button>
    <button @click="bowl(8)">8</button>
    <button @click="bowl(9)">9</button>
    <button @click="bowl(10)">10</button>

    <button @click="clear()">Clear</button>

    <h4>Backend response: {{ response }}</h4>

    <h4>
        scoringCard
    </h4>
    <table style="width:100%">
        <tr>
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>5</th>
            <th>6</th>
            <th>7</th>
            <th>8</th>
            <th>9</th>
            <th>10</th>
        </tr>
        <tr id="example-2">
            <td v-for="(item) in response.frames">
                {{ item.scores[0] }}
            </td>
        </tr>

        <tr id="example-2">
            <td v-for="(item) in response.frames">
                {{ item.scores[1] }}
            </td>
        </tr>
    </table>
    <h2>
        Total: {{ response.total}}
    </h2>
  </div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: 'game',

    data () {
      return {
          pins: [],
          msg: 'Call Game REST-Services:',
          response: [],
          errors: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
        clear() {
            this.pins = [];
            this.response = "";
        },
        bowl(numberOfPins) {
            this.pins.push (numberOfPins);
            this.response = this.pins.join(', ');
            let url = '/play?pins='+this.pins.join ('&pins=');
            console.log ('url: '+url);
            AXIOS.get(url)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    this.errors.push(e)
                })

        },
        callGameRestService () {
            url = '/play?pins='+this.pins.join ('?pins=');
            AXIOS.get(url)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    this.errors.push(e)
                })
        }
    }
  }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
